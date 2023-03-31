package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Subjects {
    private final String name;
    private final String short_name;
    private final boolean is_base_subject;

    public Subjects(final String name, String short_name, boolean is_base_subject) {
        this.name = name;
        this.short_name = short_name;
        this.is_base_subject = is_base_subject;
    }

    public Response create() throws Exception {
        JSONObject requestBody = new JSONObject();

        requestBody.put("name", name);
        requestBody.put("short_name", short_name);
        requestBody.put("is_base_subject", is_base_subject);

        Response response = Specification.sendRequest(requestBody, EndPoints.SUBJECTS_URL, Specification.requestType.POST);

        if (response.statusCode() == 201) {
            JSONParser parser = new JSONParser();

            JSONObject json;
            try {
                json = (JSONObject) parser.parse(response.getBody().asString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            int id = Integer.parseInt(json.get("id").toString());
            ConfigValue.SUBJECTS_LIST.add(id);
        }

        return response;
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.SUBJECTS_URL, ConfigValue.SUBJECTS_LIST, "id");
    }

    public static void deleteAll() throws Exception {
        Subjects.getAll();

        Specification.RESTDelete(ConfigValue.SUBJECTS_LIST, EndPoints.SUBJECTS_URL);

    }
}
