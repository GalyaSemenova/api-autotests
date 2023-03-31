package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.BellGrid;
import timetable.api.ConfigValue;
import timetable.api.Weeks;

import java.util.ArrayList;
import java.util.List;

public class WeeksTest {
    @Test
    public void createTest() throws Exception {
        BellGrid.getAll();

        List<Weeks> weeks = new ArrayList<>();
        weeks.add(new Weeks(ConfigValue.BELL_GRIDS_LIST.get(0), 1));
        weeks.add(new Weeks(ConfigValue.BELL_GRIDS_LIST.get(0), 2));
        weeks.add(new Weeks(ConfigValue.BELL_GRIDS_LIST.get(1), 1));
        weeks.add(new Weeks(ConfigValue.BELL_GRIDS_LIST.get(1), 2));
        weeks.add(new Weeks(ConfigValue.BELL_GRIDS_LIST.get(2), 1));

        for (Weeks week : weeks) {
            int actual = week.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            Weeks.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


