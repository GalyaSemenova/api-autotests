package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class CurriculumDivisionSubgroups {
    private final String name;
    private final Integer division_type_id;
    private final Integer subgroup_num;

    public CurriculumDivisionSubgroups(final String name, Integer division_type_id, Integer subgroup_num) {
        this.name = name;
        this.division_type_id = division_type_id;
        this.subgroup_num = subgroup_num;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("division_type_id", division_type_id);
        requestBody.put("subgroup_num", subgroup_num);
        requestBody.put("name", name);

        Response response = Specification.sendRequest(requestBody, EndPoints.CURRICULUM_DIVISION_SUBGROUPS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.WEEKS_LIST.add(division_type_id + " " + subgroup_num + " " + name);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.CURRICULUM_DIVISION_SUBGROUPS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.CURRICULUM_DIVISION_SUBGROUPS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String division_type_id = ((JSONObject) o).get("division_type_id").toString();
                    String subgroup_num = ((JSONObject) o).get("subgroup_num").toString();

                    ConfigValue.CURRICULUM_DIVISION_SUBGROUPS_LIST.add(division_type_id + " " + subgroup_num);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        CurriculumDivisionSubgroups.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.CURRICULUM_DIVISION_SUBGROUPS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.CURRICULUM_DIVISION_SUBGROUPS_LIST.get(i);
            int division_type_id = Integer.parseInt(val.split(" ")[0]);
            int subgroup_num = Integer.parseInt(val.split(" ")[1]);
            Response response = Specification.sendRequest(EndPoints.CURRICULUM_DIVISION_SUBGROUPS_URL + "?division_type_id=" + division_type_id + "&subgroup_num=" + subgroup_num, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.CURRICULUM_DIVISION_SUBGROUPS_LIST.remove(val);
        }
    }
}
