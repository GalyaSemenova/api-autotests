package timetable.api;

import io.restassured.response.Response;

public class ClassroomType {
    private final String name;

    public ClassroomType(final String name) {
        this.name = name;
    }

    public Response create() {
        return Specification.RESTCreate(new String[][] {{"name", name}}, EndPoints.CLASSROOM_TYPE_URL, "id", ConfigValue.CLASSROOM_TYPES_LIST);
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.CLASSROOM_TYPE_URL, ConfigValue.CLASSROOM_TYPES_LIST, "id");
    }
    public static void deleteAll() throws Exception {
        ClassroomType.getAll();
        Specification.RESTDelete(ConfigValue.CLASSROOM_TYPES_LIST, EndPoints.CLASSROOM_TYPE_URL);
    }
}
