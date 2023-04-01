package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class SubgroupTeachers {
    private final int group_id;
    private final int division_type_id;
    private final int subgroup_num;
    private final int subject_id;
    private final int teacher_id;
    public SubgroupTeachers(String group_id, String division_type_id, String subgroup_num, String subject_id, int teacher_id) {
        this.group_id = Integer.parseInt(group_id);
        this.division_type_id = Integer.parseInt(division_type_id);
        this.subgroup_num = Integer.parseInt(subgroup_num);
        this.subject_id = Integer.parseInt(subject_id);
        this.teacher_id = teacher_id;
    }



    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("group_id", group_id);
        requestBody.put("division_type_id", division_type_id);
        requestBody.put("subgroup_num", subgroup_num);
        requestBody.put("subject_id", subject_id);
        requestBody.put("teacher_id", teacher_id);

        Response response = Specification.sendRequest(requestBody, EndPoints.SUBGROUP_TEACHERS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.SUBGROUP_TEACHERS_LIST.add(group_id + " " + division_type_id + " " + subgroup_num + " " + subject_id);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.SUBGROUP_TEACHERS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.SUBGROUP_TEACHERS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String group_id = ((JSONObject) o).get("group_id").toString();
                    String division_type_id = ((JSONObject) o).get("division_type_id").toString();
                    String subgroup_num = ((JSONObject) o).get("subgroup_num").toString();
                    String subject_id = ((JSONObject) o).get("subject_id").toString();
                    String teacher_id = ((JSONObject) o).get("teacher_id").toString();

                    ConfigValue.SUBGROUP_TEACHERS_LIST.add(group_id + " " + division_type_id + " " + subgroup_num + " " + subject_id + " " + teacher_id);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        SubgroupTeachers.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.SUBGROUP_TEACHERS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.SUBGROUP_TEACHERS_LIST.get(i);
            int group_id = Integer.parseInt(val.split(" ")[0]);
            int division_type_id = Integer.parseInt(val.split(" ")[1]);
            int subgroup_num = Integer.parseInt(val.split(" ")[2]);
            int subject_id = Integer.parseInt(val.split(" ")[3]);
            Response response = Specification.sendRequest(EndPoints.SUBGROUP_TEACHERS_URL + "?group_id=" + group_id + "&division_type_id=" + division_type_id + "&subgroup_num=" + subgroup_num + "&subject_id=" + subject_id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.SUBGROUP_TEACHERS_LIST.remove(val);
        }
    }
}
