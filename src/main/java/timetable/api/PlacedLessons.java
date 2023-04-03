package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class PlacedLessons {
    private final long start_date;
    private final long end_date;
    private final int subject_id;
    private final String commentary;
    private final int teacher_id;
    private final int classroom_id;
    private final int group_id;
    private final int division_type_id;
    private final int subgroup_num;
    private final boolean is_replacement;
    private final boolean is_in_transition;
    private final boolean is_canceled;

    public PlacedLessons(long start_date, long end_date, int subject_id, String commentary, int teacher_id, int classroom_id, int group_id, int division_type_id, int subgroup_num, boolean is_replacement, boolean is_in_transition, boolean is_canceled) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.subject_id = subject_id;
        this.commentary = commentary;
        this.teacher_id = teacher_id;
        this.classroom_id = classroom_id;
        this.group_id = group_id;
        this.division_type_id = division_type_id;
        this.subgroup_num = subgroup_num;
        this.is_replacement = is_replacement;
        this.is_in_transition = is_in_transition;
        this.is_canceled = is_canceled;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("start_date", start_date);
        requestBody.put("end_date", end_date);
        requestBody.put("subject_id", subject_id);
        requestBody.put("commentary", commentary);
        requestBody.put("teacher_id", teacher_id);
        requestBody.put("classroom_id", classroom_id);
        requestBody.put("group_id", group_id);
        requestBody.put("division_type_id", division_type_id);
        requestBody.put("subgroup_num", subgroup_num);
        requestBody.put("is_replacement", is_replacement);
        requestBody.put("is_in_transition", is_in_transition);
        requestBody.put("is_canceled", is_canceled);

        Response response = Specification.sendRequest(requestBody, EndPoints.PLACED_LESSONS_URL, Specification.requestType.POST);

        JSONParser parser = new JSONParser();

        JSONObject json;
        try {
            json = (JSONObject) parser.parse(response.getBody().asString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int id = Integer.parseInt(json.get("id").toString());

        if (response.statusCode() == 201) {
            ConfigValue.PLACED_LESSONS_LIST.add(id + " " + start_date + " " + end_date + " " + subject_id + " " + commentary + " " + teacher_id + " " + classroom_id + " " + group_id + " " + division_type_id + " " + subgroup_num + " " + is_replacement + " " + is_in_transition + " " + is_canceled);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.PLACED_LESSONS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.PLACED_LESSONS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String id = ((JSONObject) o).get("id").toString();
                    String start_date = ((JSONObject) o).get("start_date").toString();
                    String end_date = ((JSONObject) o).get("end_date").toString();
                    String subject_id = ((JSONObject) o).get("subject_id").toString();
                    String commentary = ((JSONObject) o).get("commentary").toString();
                    String teacher_id = ((JSONObject) o).get("teacher_id").toString();
                    String classroom_id = ((JSONObject) o).get("classroom_id").toString();
                    String group_id = ((JSONObject) o).get("group_id").toString();
                    String division_type_id = ((JSONObject) o).get("division_type_id").toString();
                    String subgroup_num = ((JSONObject) o).get("subgroup_num").toString();
                    String is_replacement = ((JSONObject) o).get("is_replacement").toString();
                    String is_in_transition = ((JSONObject) o).get("is_in_transition").toString();
                    String is_canceled = ((JSONObject) o).get("is_canceled").toString();

                    ConfigValue.PLACED_LESSONS_LIST.add(id + " " + start_date + " " + end_date + " " + subject_id + " " + commentary + " " + teacher_id + " " + classroom_id + " " + group_id + " " + division_type_id + " " + subgroup_num + " " + is_replacement + " " + is_in_transition + " " + is_canceled);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        PlacedLessons.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.PLACED_LESSONS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.PLACED_LESSONS_LIST.get(i);
            int id = Integer.parseInt(val.split(" ")[0]);

            Response response = Specification.sendRequest(EndPoints.PLACED_LESSONS_URL + "?id=" + id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.PLACED_LESSONS_LIST.remove(val);
        }
    }
}
