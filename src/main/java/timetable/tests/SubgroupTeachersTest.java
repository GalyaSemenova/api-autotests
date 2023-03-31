package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubgroupTeachersTest {
    @Test
    public void createTest() throws Exception {
        Group.getAll();
        CurriculumDivisionTypes.getAll();
        CurriculumDivisionSubgroups.getAll();
        Teachers.getAll();
        Subjects.getAll();
        AcademicHours.getAll();

        List<SubgroupTeachers> subgroupTeachers = new ArrayList<>();

        Random rand = new Random();

        for (int l = 0; l < ConfigValue.GROUPS_LIST.size() - 1; l++) {

            for (int i = 0; i < ConfigValue.ACADEMIC_HOURS_LIST.size(); i++) {
                String group = ConfigValue.GROUPS_LIST.get(l);
                String hour = ConfigValue.ACADEMIC_HOURS_LIST.get(i);

                if ((group.split(" ")[1]).equals(hour.split(" ")[0])) {
                    subgroupTeachers.add(new SubgroupTeachers(group.split(" ")[0], hour.split(" ")[1], hour.split(" ")[2], hour.split(" ")[3], ConfigValue.TEACHERS_LIST.get(rand.nextInt(ConfigValue.TEACHERS_LIST.size()))));
                }
            }
        }
        for (SubgroupTeachers teacher : subgroupTeachers) {
            int actual = teacher.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            SubgroupTeachers.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


