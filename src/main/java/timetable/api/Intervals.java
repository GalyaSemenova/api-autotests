package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Intervals {
    private final int bell_grid_id;
    private final int week_num;
    private final int day_num;
    private final int num_on_day;
    private final int begin;
    private final int end;

    public Intervals(final String bell_grid_id, final String week_num, int day_num, int num_on_day, int begin, int end) {
        this.bell_grid_id = Integer.parseInt(bell_grid_id);
        this.week_num = Integer.parseInt(week_num);
        this.day_num = day_num;
        this.num_on_day = num_on_day;
        this.begin = begin;
        this.end = end;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("bell_grid_id", bell_grid_id);
        requestBody.put("week_num", week_num);
        requestBody.put("day_num", day_num);
        requestBody.put("num_on_day", num_on_day);
        requestBody.put("begin_minute_of_day", begin);
        requestBody.put("end_minute_of_day", end);

        Response response = Specification.sendRequest(requestBody, EndPoints.INTERVALS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            ConfigValue.INTERVALS_LIST.add(bell_grid_id + " " + week_num + " " + day_num + " " + num_on_day);
        }
        return response;
    }

    public static Response getAll() {
        Response response = Specification.sendRequest(EndPoints.INTERVALS_URL, Specification.requestType.GET);
        JSONParser parser = new JSONParser();

        if (response.statusCode() == 200) {
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray = (JSONArray) json.get("resources");

            ConfigValue.INTERVALS_LIST.clear();

            if (jsonArray != null) {
                for (Object o : jsonArray) {
                    String bell_grid_id = ((JSONObject) o).get("bell_grid_id").toString();
                    String week_num = ((JSONObject) o).get("week_num").toString();
                    String day_num = ((JSONObject) o).get("day_num").toString();
                    String num_on_day = ((JSONObject) o).get("num_on_day").toString();

                    ConfigValue.INTERVALS_LIST.add(bell_grid_id + " " + week_num + " " + day_num + " " + num_on_day);
                }
            }
        }
        return response;
    }

    public static void deleteAll() throws Exception {
        Intervals.getAll();

        List<String> deletingList = new ArrayList<>();
        for (int i = ConfigValue.INTERVALS_LIST.size() - 1; i >= 0; i--) {
            String val = ConfigValue.INTERVALS_LIST.get(i);
            int bell_grid_id = Integer.parseInt(val.split(" ")[0]);
            int week_num = Integer.parseInt(val.split(" ")[1]);
            int day_num = Integer.parseInt(val.split(" ")[2]);
            int num_on_day = Integer.parseInt(val.split(" ")[3]);
            Response response = Specification.sendRequest(EndPoints.INTERVALS_URL + "?bell_grid_id=" + bell_grid_id + "&week_num=" + week_num + "&day_num=" + day_num + "&num_on_day=" + num_on_day, Specification.requestType.DELETE);

            if (response.statusCode() == 200) {
                deletingList.add(val);
            } else throw new Exception();
        }

        for (String val : deletingList) {
            ConfigValue.INTERVALS_LIST.remove(val);
        }
    }
}
