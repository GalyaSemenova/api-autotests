package timetable.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class Specification {
  public enum requestType {
    GET,
    POST,
    PUT,
    DELETE
  }

  public static RequestSpecification requestSpec() {
    RequestSpecBuilder requestBuilder = new RequestSpecBuilder()
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON);

    return requestBuilder.build();
  }

  public static Response sendRequest(String url, requestType type) {
    Response response = null;

    switch (type) {
      case GET -> response = given()
          .spec(requestSpec())
          .when().get(url);
      case DELETE -> response = given()
          .spec(requestSpec())
          .when().delete(url);
    }
    return response;
  }

  public static Response sendRequest(JSONObject requestBody, String url, requestType type) {
    Response response = null;

    RequestSpecification requestSpec = given()
        .spec(requestSpec())
        .body(requestBody.toString());

    switch (type) {
      case POST -> response = requestSpec.when().post(url);
      case PUT -> response = requestSpec.when().put(url);
    }

    return response;
  }

  public static ResponseSpecification checkStatus(int statusCode) {
    return new ResponseSpecBuilder()
        .expectStatusCode(statusCode)
        .build();
  }
}
