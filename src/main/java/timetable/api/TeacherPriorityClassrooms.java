package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class TeacherPriorityClassrooms {
    private final int teacher_id;
    private final int classroom_id;

    public TeacherPriorityClassrooms(int teacher_id, int classroom_id) {
        this.teacher_id = teacher_id;
        this.classroom_id = classroom_id;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("teacher_id", teacher_id);
        requestBody.put("classroom_id", classroom_id);

        Response response = Specification.sendRequest(requestBody, EndPoints.TEACHER_PRIORITY_CLASSROOMS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.TEACHER_PRIORITY_CLASSROOMS_LIST.add(teacher_id + " " + classroom_id);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.TEACHER_PRIORITY_CLASSROOMS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.TEACHER_PRIORITY_CLASSROOMS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String teacher_id = ((JSONObject) o).get("teacher_id").toString();
                    String classroom_id = ((JSONObject) o).get("classroom_id").toString();

                    ConfigValue.TEACHER_PRIORITY_CLASSROOMS_LIST.add(teacher_id + " " + classroom_id);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        TeacherPriorityClassrooms.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.TEACHER_PRIORITY_CLASSROOMS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.TEACHER_PRIORITY_CLASSROOMS_LIST.get(i);
            int teacher_id = Integer.parseInt(val.split(" ")[0]);
            int classroom_id = Integer.parseInt(val.split(" ")[1]);
            Response response = Specification.sendRequest(EndPoints.TEACHER_PRIORITY_CLASSROOMS_URL + "?teacher_id=" + teacher_id + "&classroom_id=" + classroom_id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.TEACHER_PRIORITY_CLASSROOMS_LIST.remove(val);
        }
    }
}
