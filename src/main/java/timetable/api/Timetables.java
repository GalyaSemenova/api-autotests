package timetable.api;

import io.restassured.response.Response;

public class Timetables {
    private final String name;

    public Timetables(final String name) {
        this.name = name;
    }

    public Response create() throws Exception {
        return Specification.RESTCreate(new String[][]{{"name", name}}, EndPoints.TIMETABLES_URL, "id", ConfigValue.TIMETABLES_LIST);
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.TIMETABLES_URL, ConfigValue.TIMETABLES_LIST, "id");
    }

    public static void deleteAll() throws Exception {
        Timetables.getAll();

        Specification.RESTDelete(ConfigValue.TIMETABLES_LIST, EndPoints.TIMETABLES_URL);

    }
}
