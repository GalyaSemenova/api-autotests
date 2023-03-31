package timetable.api;

import io.restassured.response.Response;

public class AnnualCurriculums {
    private final String name;
    private final int year_of_study;

    public AnnualCurriculums(final String name, int year_of_study) {
        this.name = name;
        this.year_of_study = year_of_study;
    }

    public Response create() throws Exception {
        return Specification.RESTCreate(new String[][]{{"name", name}, {"year_of_study", String.valueOf(year_of_study)}}, EndPoints.ANNUAL_CURRICULUMS_URL, "id", ConfigValue.ANNUAL_CURRICULUMS_LIST);
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.ANNUAL_CURRICULUMS_URL, ConfigValue.ANNUAL_CURRICULUMS_LIST, "id");
    }

    public static void deleteAll() throws Exception {
        AnnualCurriculums.getAll();

        Specification.RESTDelete(ConfigValue.ANNUAL_CURRICULUMS_LIST, EndPoints.ANNUAL_CURRICULUMS_URL);

    }
}
