package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class GroupPriorityClassrooms {
    private final int group_id;
    private final int classroom_id;

    public GroupPriorityClassrooms(String group_id, int classroom_id) {
        this.group_id = Integer.parseInt(group_id);
        this.classroom_id = classroom_id;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("group_id", group_id);
        requestBody.put("classroom_id", classroom_id);

        Response response = Specification.sendRequest(requestBody, EndPoints.GROUPS_PRIORITY_CLASSROOMS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.GROUPS_PRIORITY_CLASSROOMS_LIST.add(group_id + " " + classroom_id);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.GROUPS_PRIORITY_CLASSROOMS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.GROUPS_PRIORITY_CLASSROOMS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String group_id = ((JSONObject) o).get("group_id").toString();
                    String classroom_id = ((JSONObject) o).get("classroom_id").toString();

                    ConfigValue.GROUPS_PRIORITY_CLASSROOMS_LIST.add(group_id + " " + classroom_id);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        GroupPriorityClassrooms.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.GROUPS_PRIORITY_CLASSROOMS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.GROUPS_PRIORITY_CLASSROOMS_LIST.get(i);
            int group_id = Integer.parseInt(val.split(" ")[0]);
            int classroom_id = Integer.parseInt(val.split(" ")[1]);
            Response response = Specification.sendRequest(EndPoints.GROUPS_PRIORITY_CLASSROOMS_URL + "?group_id=" + group_id + "&classroom_id=" + classroom_id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.GROUPS_PRIORITY_CLASSROOMS_LIST.remove(val);
        }
    }
}
