package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Lessons {
    private final int subject_id;
    private final int group_id;
    private final int division_type_id;
    private final int subgroup_num;
    private final int timetable_id;
    private final int classroom_id;
    private final int teacher_id;
    private final int bell_grid_id;
    private final int week_num;
    private final int day_num;
    private final int num_on_day;



    public Lessons(String group_id, String division_type_id, String subgroup_num, String subject_id, int timetable_id, int classroom_id, int teacher_id, int bell_grid_id, int week_num, int day_num, int num_on_day) {
        this.group_id = Integer.parseInt(group_id);
        this.division_type_id = Integer.parseInt(division_type_id);
        this.subgroup_num = Integer.parseInt(subgroup_num);
        this.subject_id = Integer.parseInt(subject_id);
        this.timetable_id = timetable_id;
        this.classroom_id = classroom_id;
        this.teacher_id = teacher_id;
        this.bell_grid_id = bell_grid_id;
        this.week_num = week_num;
        this.day_num = day_num;
        this.num_on_day = num_on_day;
    }

    public Lessons(int group_id, int division_type_id, int subgroup_num, int subject_id, int timetable_id, int classroom_id, int teacher_id, int bell_grid_id, int week_num, int day_num, int num_on_day) {
        this.group_id = group_id;
        this.division_type_id = division_type_id;
        this.subgroup_num = subgroup_num;
        this.subject_id = subject_id;
        this.timetable_id = timetable_id;
        this.classroom_id = classroom_id;
        this.teacher_id = teacher_id;
        this.bell_grid_id = bell_grid_id;
        this.week_num = week_num;
        this.day_num = day_num;
        this.num_on_day = num_on_day;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();


        requestBody.put("group_id", group_id);
        requestBody.put("division_type_id", division_type_id);
        requestBody.put("subgroup_num", subgroup_num);
        requestBody.put("subject_id", subject_id);
        requestBody.put("timetable_id", timetable_id);
        requestBody.put("classroom_id", classroom_id);
        requestBody.put("teacher_id", teacher_id);
        requestBody.put("bell_grid_id", bell_grid_id);
        requestBody.put("week_num", week_num);
        requestBody.put("day_num", day_num);
        requestBody.put("num_on_day", num_on_day);

        Response response = Specification.sendRequest(requestBody, EndPoints.LESSONS_URL, Specification.requestType.POST);

        JSONParser parser = new JSONParser();

        JSONObject json;
        try {
            json = (JSONObject) parser.parse(response.getBody().asString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int id = Integer.parseInt(json.get("id").toString());

        if (response.statusCode() == 201) {
            ConfigValue.LESSONS_LIST.add(id + " " + group_id + " " + division_type_id + " " + subgroup_num + " " + subject_id + " " + timetable_id + " " + classroom_id + " " + teacher_id + " " + bell_grid_id + " " + week_num + " " + day_num + " " + num_on_day);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.LESSONS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.LESSONS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String id = ((JSONObject) o).get("id").toString();
                    String group_id = ((JSONObject) o).get("group_id").toString();
                    String division_type_id = ((JSONObject) o).get("division_type_id").toString();
                    String subgroup_num = ((JSONObject) o).get("subgroup_num").toString();
                    String subject_id = ((JSONObject) o).get("subject_id").toString();
                    String timetable_id = ((JSONObject) o).get("timetable_id").toString();
                    String classroom_id = ((JSONObject) o).get("classroom_id").toString();
                    String teacher_id = ((JSONObject) o).get("teacher_id").toString();
                    String bell_grid_id = ((JSONObject) o).get("bell_grid_id").toString();
                    String week_num = ((JSONObject) o).get("week_num").toString();
                    String day_num = ((JSONObject) o).get("day_num").toString();
                    String num_on_day = ((JSONObject) o).get("num_on_day").toString();

                    ConfigValue.LESSONS_LIST.add(id + " " + group_id + " " + division_type_id + " " + subgroup_num + " " + subject_id + " " + timetable_id + " " + classroom_id + " " + teacher_id + " " + bell_grid_id + " " + week_num + " " + day_num + " " + num_on_day);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        Lessons.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.LESSONS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.LESSONS_LIST.get(i);
            int id = Integer.parseInt(val.split(" ")[0]);

            Response response = Specification.sendRequest(EndPoints.LESSONS_URL + "?id=" + id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.LESSONS_LIST.remove(val);
        }
    }
}
