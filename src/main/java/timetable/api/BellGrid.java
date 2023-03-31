package timetable.api;

import io.restassured.response.Response;
public class BellGrid {
    private final String name;

    public BellGrid(final String name) {
        this.name = name;
    }

    public Response create() throws Exception {
        return Specification.RESTCreate(new String[][]{{"name", name}}, EndPoints.BELL_GRID_URL, "id", ConfigValue.BELL_GRIDS_LIST);
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.BELL_GRID_URL, ConfigValue.BELL_GRIDS_LIST, "id");
    }

    public static void deleteAll() throws Exception {
        BellGrid.getAll();

        Specification.RESTDelete(ConfigValue.BELL_GRIDS_LIST, EndPoints.BELL_GRID_URL);

    }
}
