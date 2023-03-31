package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.AnnualCurriculums;
import timetable.api.BellGrid;
import timetable.api.ConfigValue;
import timetable.api.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupsTest {

    @Test
    public void createTest() throws Exception {
        List<Group> groups = new ArrayList<>();
        Random rand = new Random();

        AnnualCurriculums.getAll();
        BellGrid.getAll();

        for (int i = 0; i < 20; i++) {
            int id = rand.nextInt(ConfigValue.ANNUAL_CURRICULUMS_LIST.size());
            int id2 = rand.nextInt(ConfigValue.BELL_GRIDS_LIST.size());

            groups.add(new Group(i % 3 == 0 ? "а" : i % 3 == 1 ? "б" : "в",ConfigValue.ANNUAL_CURRICULUMS_LIST.get(id), ConfigValue.BELL_GRIDS_LIST.get(id2)));
        }

        for (Group group : groups) {
            int actual = group.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            Group.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


