package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllowedClassroomTypesTest {
    @Test
    public void createTest() throws Exception {
        Random rand = new Random();
        ClassroomType.getAll();
        Subjects.getAll();

        var typesList = ConfigValue.CLASSROOM_TYPES_LIST;

        List<AllowedClassroomTypes> types = new ArrayList<>();
        for (int i = 0; i < ConfigValue.SUBJECTS_LIST.size(); i++) {
                int typeId = rand.nextInt(typesList.size());
                types.add(new AllowedClassroomTypes(i + 1, typesList.get(typeId)));
        }

        for (AllowedClassroomTypes type : types) {
            int actual = type.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            AllowedClassroomTypes.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


