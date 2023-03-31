package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DifficultyPointsTest {
    @Test
    public void createTest() throws Exception {
        Random rand = new Random();
        Subjects.getAll();

        List<DifficultyPoints> points = new ArrayList<>();

        for (int i = 0; i < ConfigValue.SUBJECTS_LIST.size(); i++) {
            int dif = rand.nextInt(10) + 1;
            int groupsNumber = rand.nextInt(11) + 1;
            for (int j = 0; j < groupsNumber; j++) {
                points.add(new DifficultyPoints(ConfigValue.SUBJECTS_LIST.get(i), rand.nextInt(11) + 1, dif));
            }
        }

        for (DifficultyPoints point : points) {
            int actual = point.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            DifficultyPoints.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


