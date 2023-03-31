package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Weeks {
    private final int bell_grid_id;
    private final int num;

    public Weeks(final int bell_grid_id, final int num) {
        this.bell_grid_id = bell_grid_id;
        this.num = num;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("bell_grid_id", bell_grid_id);
        requestBody.put("num", num);

        Response response = Specification.sendRequest(requestBody, EndPoints.WEEKS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.WEEKS_LIST.add(bell_grid_id + " " + num);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.WEEKS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.WEEKS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String bell_grid_id = ((JSONObject) o).get("bell_grid_id").toString();
                    String num = ((JSONObject) o).get("num").toString();

                    ConfigValue.WEEKS_LIST.add(bell_grid_id + " " + num);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        Weeks.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.WEEKS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.WEEKS_LIST.get(i);
            int bell_grid_id = Integer.parseInt(val.split(" ")[0]);
            int num = Integer.parseInt(val.split(" ")[1]);
            Response response = Specification.sendRequest(EndPoints.WEEKS_URL + "?bell_grid_id=" + bell_grid_id + "&num=" + num, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.WEEKS_LIST.remove(val);
        }
    }
}
