package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ClassroomType {
    private final String name;

    public ClassroomType(final String name) {
        this.name = name;
    }

    public Response create() {
        JSONObject requestBody = new JSONObject();
        JSONParser parser = new JSONParser();
        requestBody.put("name", name);

        Response response = Specification.sendRequest(requestBody, EndPoints.CLASSROOM_TYPE_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ConfigValue.CLASSROOM_TYPES_LIST.add(Integer.parseInt(json.get("id").toString()));
        }

        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.CLASSROOM_TYPE_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.CLASSROOM_TYPES_LIST.clear();

            if (jsonArray != null) {
                int len = jsonArray.size();
                for (int i = 0; i < len; i++) {
                    String id = ((JSONObject) jsonArray.get(i)).get("id").toString();

                    ConfigValue.CLASSROOM_TYPES_LIST.add(Integer.parseInt(id));
                }
            }
        }
        return response;
    }
    public static void deleteAll() throws Exception {
        ClassroomType.getAll();

        List<Integer> deletingList = new ArrayList<>();
        for(int id : ConfigValue.CLASSROOM_TYPES_LIST) {
            Response response = Specification.sendRequest(EndPoints.CLASSROOM_TYPE_URL + "?id=" + id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(id);
            } else throw new Exception();
        }

        for (int id : deletingList) {
            ConfigValue.CLASSROOM_TYPES_LIST.remove(ConfigValue.CLASSROOM_TYPES_LIST.indexOf(id));
        }
    }
}
