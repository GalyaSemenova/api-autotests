package timetable.api;

import io.restassured.response.Response;

public class CurriculumDivisionTypes {
    private final String name;

    public CurriculumDivisionTypes(final String name) {
        this.name = name;
    }

    public Response create() throws Exception {
        return Specification.RESTCreate(new String[][] {{"name", name}}, EndPoints.CURRICULUM_DIVISION_TYPES_URL, "id", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST);
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.CURRICULUM_DIVISION_TYPES_URL, ConfigValue.CURRICULUM_DIVISION_TYPES_LIST, "id");
    }

    public static void deleteAll() throws Exception {
        CurriculumDivisionTypes.getAll();

        Specification.RESTDelete(ConfigValue.CURRICULUM_DIVISION_TYPES_LIST, EndPoints.CURRICULUM_DIVISION_TYPES_URL);

    }
}
