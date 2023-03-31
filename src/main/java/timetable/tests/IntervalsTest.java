package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.BellGrid;
import timetable.api.ConfigValue;
import timetable.api.Intervals;
import timetable.api.Weeks;

import java.util.ArrayList;
import java.util.List;

public class IntervalsTest {
    @Test
    public void createTest() throws Exception {
        BellGrid.getAll();
        Weeks.getAll();

        List<Intervals> intervals = new ArrayList<>();

        for (int l = 0; l < ConfigValue.WEEKS_LIST.size() - 1; l++) {
            for (int k = 0; k < 5; k++) {
                for (int i = 0; i < 6; i++) {
                    intervals.add(new Intervals(ConfigValue.WEEKS_LIST.get(l).split(" ")[0], ConfigValue.WEEKS_LIST.get(l).split(" ")[1], k + 1, i + 1, 440 + 55 * i, 485 + 55 * i));
                }
            }
        }
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 4; i++) {
                intervals.add(new Intervals(ConfigValue.WEEKS_LIST.get(4).split(" ")[0], ConfigValue.WEEKS_LIST.get(2).split(" ")[1], k + 1, i + 1, 440 + 45 * i, 475 + 45 * i));
            }
        }

        for (Intervals interval : intervals) {
            int actual = interval.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            Intervals.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


