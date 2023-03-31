package timetable.api;

import io.restassured.response.Response;

public class Teachers {
    private final String name;
    private final String surname;
    private final String patronymic;
    private final String description;

    public Teachers(final String name, String surname, String patronymic, String description) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.description = description;
    }

    public Response create() throws Exception {
        return Specification.RESTCreate(new String[][]{{"name", name},{"surname", surname},{"patronymic", patronymic},{"description", description}}, EndPoints.TEACHERS_URL, "id", ConfigValue.TEACHERS_LIST);
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.TEACHERS_URL, ConfigValue.TEACHERS_LIST, "id");
    }

    public static void deleteAll() throws Exception {
        Teachers.getAll();

        Specification.RESTDelete(ConfigValue.TEACHERS_LIST, EndPoints.TEACHERS_URL);

    }
}
