package timetable.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Specification {
    public enum requestType {
        GET,
        POST,
        PUT,
        DELETE
    }

    public static RequestSpecification requestSpec() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON);

        return requestBuilder.build();
    }

    public static Response sendRequest(String url, requestType type) {
        Response response = null;

        switch (type) {
            case GET -> response = given()
                    .spec(requestSpec())
                    .when().get(url);
            case DELETE -> response = given()
                    .spec(requestSpec())
                    .when().delete(url);
        }
        return response;
    }

    public static Response sendRequest(JSONObject requestBody, String url, requestType type) {
        Response response = null;

        RequestSpecification requestSpec = given()
                .spec(requestSpec())
                .body(requestBody.toString());

        switch (type) {
            case POST -> response = requestSpec.when().post(url);
            case PUT -> response = requestSpec.when().put(url);
        }

        return response;
    }

    public static Response RESTCreate(String[][] params, String endPoints, String receiveParam, List<Integer> list) {
        JSONObject requestBody = new JSONObject();
        for (String[] param : params) {
            requestBody.put(param[0],
                    param[0].equals("capacity") || param[0].equals("id") || param[0].equals("classroom_type_id") || param[0].equals("classroom_id") || param[0].equals("bell_grid_id") || param[0].equals("num") || param[0].equals("year_of_study") || param[0].equals("annual_curriculum_id") ? Integer.parseInt(param[1]) : param[1]);
        }

        Response response = Specification.sendRequest(requestBody, endPoints, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            JSONParser parser = new JSONParser();

            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (receiveParam != null) {
                int id = Integer.parseInt(json.get(receiveParam).toString());
                if (list != null) {
                    list.add(id);
                }
            }
        }
        return response;
    }

    public static Response RESTGet(String url, List<Integer> list, String receiveParam) {
        Response response = Specification.sendRequest(url, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            list.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String id = ((JSONObject) o).get(receiveParam).toString();

                    list.add(Integer.parseInt(id));
                }
            }
        }
        return response;
    }

    public static void RESTDelete(List<Integer> list, String url) throws Exception {

        List<Integer> deletingList = new ArrayList<>();
        for (int id : list) {
            Response response = Specification.sendRequest(url + "?id=" + id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(id);
            } else throw new Exception();
        }

        for (int id : deletingList) {
            list.remove((Integer) id);
        }
    }
}
