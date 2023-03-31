package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class AllowedClassroomTypes {
    private final int subject_id;
    private final int classroom_type_id;

    public AllowedClassroomTypes(int subject_id, int classroom_type_id) {
        this.subject_id = subject_id;
        this.classroom_type_id = classroom_type_id;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("subject_id", subject_id);
        requestBody.put("classroom_type_id", classroom_type_id);

        Response response = Specification.sendRequest(requestBody, EndPoints.ALLOWED_CLASSROOM_TYPES_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.ALLOWED_CLASSROOM_TYPES_LIST.add(subject_id + " " + classroom_type_id);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.ALLOWED_CLASSROOM_TYPES_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.ALLOWED_CLASSROOM_TYPES_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String subject_id = ((JSONObject) o).get("subject_id").toString();
                    String classroom_type_id = ((JSONObject) o).get("classroom_type_id").toString();

                    ConfigValue.ALLOWED_CLASSROOM_TYPES_LIST.add(subject_id + " " + classroom_type_id);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        AllowedClassroomTypes.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.ALLOWED_CLASSROOM_TYPES_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.ALLOWED_CLASSROOM_TYPES_LIST.get(i);
            int subject_id = Integer.parseInt(val.split(" ")[0]);
            int classroom_type_id = Integer.parseInt(val.split(" ")[1]);
            Response response = Specification.sendRequest(EndPoints.ALLOWED_CLASSROOM_TYPES_URL + "?subject_id=" + subject_id + "&classroom_type_id=" + classroom_type_id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.ALLOWED_CLASSROOM_TYPES_LIST.remove(val);
        }
    }
}
