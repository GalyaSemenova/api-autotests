package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AcademicHoursTest {

    @Test
    public void createTest() throws Exception {
        AnnualCurriculums.getAll();
        CurriculumDivisionTypes.getAll();
        CurriculumDivisionSubgroups.getAll();
        Subjects.getAll();
        Random rand = new Random();

        List<AcademicHours> academicHours = new ArrayList<>();
        for (int i = 0; i < ConfigValue.ANNUAL_CURRICULUMS_LIST.size(); i++) {
            var list = ConfigValue.CURRICULUM_DIVISION_SUBGROUPS_LIST;
            for (String s : list) {
                for (int j = 0; j < ConfigValue.SUBJECTS_LIST.size(); j++) {
                    int hour = rand.nextInt((150 - 80) + 1) + 80;
                    int id = ConfigValue.SUBJECTS_LIST.get(j);
                    academicHours.add(new AcademicHours(i + 1, Integer.parseInt(s.split(" ")[0]), Integer.parseInt(s.split(" ")[1]), id, hour));
                }
            }
        }

        for (AcademicHours academicHour : academicHours) {
            int actual = academicHour.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            AcademicHours.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
