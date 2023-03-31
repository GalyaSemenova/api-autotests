package timetable.api;

import io.restassured.response.Response;

public class Classroom {
    private final String name;
    private final String description;
    private final int capacity;

    private final int[] selectedTypes;

    public Classroom(final String name, final String description, final int capacity, final int[] selectedTypes) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.selectedTypes = selectedTypes;
    }

    public Response create() throws Exception {
        Response response = Specification.RESTCreate(new String[][]{{"name", name}, {"description", description}, {"capacity", String.valueOf(capacity)}}, EndPoints.CLASSROOM_URL, "id", ConfigValue.CLASSROOMS_LIST);

        int id = ConfigValue.CLASSROOMS_LIST.get(ConfigValue.CLASSROOMS_LIST.size() - 1);

        for (int selectedType : selectedTypes) {
            Specification.RESTCreate(new String[][]{{"classroom_id", String.valueOf(id)}, {"classroom_type_id", String.valueOf(selectedType)}}, EndPoints.CLASSROOM_SELECTED_TYPES_URL, null, null);
        }
        return response;
    }

    public static Response getAll() {
        return Specification.RESTGet(EndPoints.CLASSROOM_URL, ConfigValue.CLASSROOMS_LIST, "id");
    }

    public static void deleteAll() throws Exception {
        Classroom.getAll();

        Specification.RESTDelete(ConfigValue.CLASSROOMS_LIST, EndPoints.CLASSROOM_URL);
    }
}
