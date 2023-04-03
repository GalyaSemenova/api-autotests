package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.AnnualCurriculums;
import timetable.api.BellGrid;
import timetable.api.ConfigValue;
import timetable.api.Group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GroupsTest {
    private HashSet<Object> n;

    @Test
    public void createTest() throws Exception {

        List<Group> groups = new ArrayList<>();
        Random rand = new Random();

        AnnualCurriculums.getAll();
        BellGrid.getAll();

        var cur = ConfigValue.ANNUAL_CURRICULUMS_LIST;

        for (int i = 0; i < cur.size(); i++) {
            int id2 = rand.nextInt(ConfigValue.BELL_GRIDS_LIST.size());
            groups.add(new Group("а", cur.get(i), ConfigValue.BELL_GRIDS_LIST.get(id2)));
            groups.add(new Group("б", cur.get(i), ConfigValue.BELL_GRIDS_LIST.get(id2)));
            groups.add(new Group("в", cur.get(i), ConfigValue.BELL_GRIDS_LIST.get(id2)));
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

