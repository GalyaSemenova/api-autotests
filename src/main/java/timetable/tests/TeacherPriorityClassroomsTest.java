package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeacherPriorityClassroomsTest {
    @Test
    public void createTest() throws Exception {
        Random rand = new Random();
        Teachers.getAll();
        Classroom.getAll();
        List<TeacherPriorityClassrooms> classrooms = new ArrayList<>();
        for (int i = 0; i < ConfigValue.TEACHERS_LIST.size(); i++) {
            int classroomId = rand.nextInt(ConfigValue.CLASSROOMS_LIST.size()) + 1;
            classrooms.add(new TeacherPriorityClassrooms(ConfigValue.TEACHERS_LIST.get(i), classroomId));
        }

        for (TeacherPriorityClassrooms classroom : classrooms) {
            int actual = classroom.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            TeacherPriorityClassrooms.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


