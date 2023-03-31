package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String group_index;
    private final Integer annual_curriculum_id;
    private final Integer bell_grid_id;

    public Group(String group_index, Integer annual_curriculum_id, Integer bell_grid_id) {
        this.group_index = group_index;
        this.annual_curriculum_id = annual_curriculum_id;
        this.bell_grid_id = bell_grid_id;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("group_index", group_index);
        requestBody.put("annual_curriculum_id", annual_curriculum_id);
        requestBody.put("bell_grid_id", bell_grid_id);

        Response response = Specification.sendRequest(requestBody, EndPoints.GROUPS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            JSONParser parser = new JSONParser();

            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            int id = Integer.parseInt(json.get("id").toString());
            ConfigValue.GROUPS_LIST.add(id + " " + annual_curriculum_id);

        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.GROUPS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.GROUPS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String id = ((JSONObject) o).get("id").toString();
                    String annual_curriculum_id = ((JSONObject) o).get("annual_curriculum_id").toString();

                    ConfigValue.GROUPS_LIST.add(id + " " + annual_curriculum_id);
                }
            }
        }
        return response;

    }

    public static void deleteAll() throws Exception {
        Group.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.GROUPS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.GROUPS_LIST.get(i);
            int id = Integer.parseInt(val.split(" ")[0]);
            Response response = Specification.sendRequest(EndPoints.GROUPS_URL + "?id=" + id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.GROUPS_LIST.remove(val);
        }
    }
}
