package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupPriorityClassroomsTest {
    @Test
    public void createTest() throws Exception {
        Random rand = new Random();
        Group.getAll();
        Classroom.getAll();
        List<GroupPriorityClassrooms> classrooms = new ArrayList<>();
        for (int i = 0; i < ConfigValue.GROUPS_LIST.size(); i++) {
            int classroomId = rand.nextInt(ConfigValue.CLASSROOMS_LIST.size()) + 1;
            classrooms.add(new GroupPriorityClassrooms(ConfigValue.GROUPS_LIST.get(i).split(" ")[0], classroomId));
        }

        for (GroupPriorityClassrooms classroom : classrooms) {
            int actual = classroom.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            GroupPriorityClassrooms.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


