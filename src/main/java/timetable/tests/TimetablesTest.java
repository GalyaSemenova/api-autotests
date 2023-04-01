package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.ConfigValue;
import timetable.api.Timetables;

import java.util.ArrayList;
import java.util.List;

public class TimetablesTest {

    @Test
    public void createTest() throws Exception {
        List<Timetables> timetables = new ArrayList<>();

        timetables.add(new Timetables("Расписание 2023"));


        for (Timetables timetable : timetables) {
            int actual = timetable.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            Timetables.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


