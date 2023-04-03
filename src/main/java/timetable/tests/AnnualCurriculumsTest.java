package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.AnnualCurriculums;
import timetable.api.ConfigValue;

import java.util.ArrayList;
import java.util.List;

public class AnnualCurriculumsTest {

    @Test
    public void createTest() throws Exception {
        List<AnnualCurriculums> annualCurriculums = new ArrayList<>();

        annualCurriculums.add(new AnnualCurriculums("1 класс", 1));
        annualCurriculums.add(new AnnualCurriculums("2 класс", 2));
        annualCurriculums.add(new AnnualCurriculums("3 класс", 3));
        annualCurriculums.add(new AnnualCurriculums("4 класс", 4));
        annualCurriculums.add(new AnnualCurriculums("5 класс", 5));
        annualCurriculums.add(new AnnualCurriculums("6 класс", 6));
        annualCurriculums.add(new AnnualCurriculums("7 класс", 7));
        annualCurriculums.add(new AnnualCurriculums("8 класс", 8));

        for (AnnualCurriculums annualCurriculum : annualCurriculums) {
            int actual = annualCurriculum.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            AnnualCurriculums.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


