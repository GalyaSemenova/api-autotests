package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class DifficultyPoints {
    private final int subject_id;
    private final int year_of_study;
    private final int points;

    public DifficultyPoints(int subject_id, int year_of_study, int points) {
        this.subject_id = subject_id;
        this.year_of_study = year_of_study;

        this.points = points;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("subject_id", subject_id);
        requestBody.put("year_of_study", year_of_study);
        requestBody.put("points", points);

        Response response = Specification.sendRequest(requestBody, EndPoints.DIFFICULTY_POINTS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.DIFFICULTY_POINTS_LIST.add(subject_id + " " + year_of_study);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.DIFFICULTY_POINTS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.DIFFICULTY_POINTS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String subject_id = ((JSONObject) o).get("subject_id").toString();
                    String year_of_study = ((JSONObject) o).get("year_of_study").toString();

                    ConfigValue.DIFFICULTY_POINTS_LIST.add(subject_id + " " + year_of_study);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        DifficultyPoints.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.DIFFICULTY_POINTS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.DIFFICULTY_POINTS_LIST.get(i);
            int subject_id = Integer.parseInt(val.split(" ")[0]);
            int year_of_study = Integer.parseInt(val.split(" ")[1]);
            Response response = Specification.sendRequest(EndPoints.DIFFICULTY_POINTS_URL + "?subject_id=" + subject_id + "&year_of_study=" + year_of_study, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.DIFFICULTY_POINTS_LIST.remove(val);
        }
    }
}
