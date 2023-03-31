package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class AcademicHours {

    private final int annual_curriculum_id;
    private final int division_type_id;
    private final int subgroup_num;
    private final int subject_id;
    private final int amount;

    public AcademicHours(final int annual_curriculum_id, int division_type_id, int subgroup_num, int subject_id, int amount) {
        this.annual_curriculum_id = annual_curriculum_id;
        this.division_type_id = division_type_id;
        this.subgroup_num = subgroup_num;
        this.subject_id = subject_id;
        this.amount = amount;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("annual_curriculum_id", annual_curriculum_id);
        requestBody.put("division_type_id", division_type_id);
        requestBody.put("subgroup_num", subgroup_num);
        requestBody.put("subject_id", subject_id);
        requestBody.put("amount", amount);

        Response response = Specification.sendRequest(requestBody, EndPoints.ACADEMIC_HOURS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.ACADEMIC_HOURS_LIST.add(annual_curriculum_id + " " + division_type_id + " " + subgroup_num + " " + subject_id);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.ACADEMIC_HOURS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.ACADEMIC_HOURS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String annual_curriculum_id = ((JSONObject) o).get("annual_curriculum_id").toString();
                    String division_type_id = ((JSONObject) o).get("division_type_id").toString();
                    String subgroup_num = ((JSONObject) o).get("subgroup_num").toString();
                    String subject_id = ((JSONObject) o).get("subject_id").toString();

                    ConfigValue.ACADEMIC_HOURS_LIST.add(annual_curriculum_id + " " + division_type_id + " " + subgroup_num + " " + subject_id);
                }
            }
        }
        return response;

    }

    public static void deleteAll() throws Exception {
        AcademicHours.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.ACADEMIC_HOURS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.ACADEMIC_HOURS_LIST.get(i);
            int annual_curriculum_id = Integer.parseInt(val.split(" ")[0]);
            int division_type_id = Integer.parseInt(val.split(" ")[1]);
            int subgroup_num = Integer.parseInt(val.split(" ")[2]);
            int subject_id = Integer.parseInt(val.split(" ")[3]);
            Response response = Specification.sendRequest(EndPoints.ACADEMIC_HOURS_URL + "?annual_curriculum_id=" + annual_curriculum_id + "&division_type_id=" + division_type_id + "&subgroup_num=" + subgroup_num + "&subject_id=" + subject_id, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.ACADEMIC_HOURS_LIST.remove(val);
        }
    }
}
