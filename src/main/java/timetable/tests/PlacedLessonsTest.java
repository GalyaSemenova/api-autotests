package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlacedLessonsTest {

    @Test
    public void createTest() throws Exception {

        List<PlacedLessons> placedLessons = new ArrayList<>();
//        placedLessons.add(new PlacedLessons());

        for (PlacedLessons placedLesson : placedLessons) {
            int actual = placedLesson.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }

    }

    @Test
    public void deleteTest() {
        try {
            PlacedLessons.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


