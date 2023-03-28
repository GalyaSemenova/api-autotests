package timetable.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import timetable.api.Classroom;
import timetable.api.ClassroomType;
import timetable.api.ConfigValue;

import java.util.ArrayList;
import java.util.List;

public class ClassroomTest {

    @Before
    public void getters() {
        ClassroomType.getAll();
    }

    @Test
    public void createTest() throws Exception {
        List<Classroom> classrooms = new ArrayList<>();
        for (int i = 0; i < ConfigValue.CLASSROOM_TYPES_LIST.size() * 3; i++) {
            classrooms.add(new Classroom("Каб 10" + i, "", 30, new int[] {ConfigValue.CLASSROOM_TYPES_LIST.get(i % 3)}));
        }

        for (Classroom classroom : classrooms) {
            int actual = classroom.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            Classroom.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


