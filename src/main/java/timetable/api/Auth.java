package timetable.api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class Auth {
    private final String login;
    private final String password;

    public Auth(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public Response login() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("login", login);
        requestBody.put("password", password);

        Response response = Specification.sendRequest(requestBody, EndPoints.LOGIN_URL, Specification.requestType.POST);
        System.out.println(response);
        ConfigValue.AUTH = response.statusCode() == 200 ? true : false;
        return response;
    }
}
