package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private final String name;
    private final String description;
    private final int capacity;

    private final int[] selectedTypes;

    public Classroom(final String name, final String description, final int capacity, final int[] selectedTypes) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.selectedTypes = selectedTypes;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("description", description);
        requestBody.put("capacity", capacity);

        Response response = Specification.sendRequest(requestBody, EndPoints.CLASSROOM_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            JSONParser parser = new JSONParser();

            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            int id = Integer.parseInt(json.get("id").toString());
            ConfigValue.CLASSROOMS_LIST.add(id);

            JSONObject requestBody2 = new JSONObject();
            for (int i = 0; i < selectedTypes.length; i++) {
                requestBody2.put("classroom_id", id);
                requestBody2.put("classroom_type_id", selectedTypes[i]);

                Response response2 = Specification.sendRequest(requestBody2, EndPoints.CLASSROOM_SELECTED_TYPES_URL, Specification.requestType.POST);

                if (response2.statusCode() != 201) {
                    throw new Exception();
                }
            }
        }

        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.CLASSROOM_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.CLASSROOMS_LIST.clear();

            if (jsonArray != null) {
                int len = jsonArray.size();
                for (int i = 0; i < len; i++) {
                    String id = ((JSONObject) jsonArray.get(i)).get("id").toString();

                    ConfigValue.CLASSROOMS_LIST.add(Integer.parseInt(id));
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        Classroom.getAll();

        List<Integer> deletingList = new ArrayList<>();
        for(int id : ConfigValue.CLASSROOMS_LIST) {
            Response response = Specification.sendRequest(EndPoints.CLASSROOM_URL + "?id=" + id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(id);
            } else throw new Exception();
        }

        for (int id : deletingList) {
            ConfigValue.CLASSROOMS_LIST.remove(ConfigValue.CLASSROOMS_LIST.indexOf(id));
        }
        System.out.println(ConfigValue.CLASSROOMS_LIST);
    }
}
