package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.ConfigValue;
import timetable.api.CurriculumDivisionTypes;

import java.util.ArrayList;
import java.util.List;

public class CurriculumDivisionTypesTest {

    @Test
    public void createTest() throws Exception {
        List<CurriculumDivisionTypes> types = new ArrayList<>();

        types.add(new CurriculumDivisionTypes("По полу"));
        types.add(new CurriculumDivisionTypes("Основное деление"));
        types.add(new CurriculumDivisionTypes("Уровень англ"));

        for (CurriculumDivisionTypes type : types) {
            int actual = type.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            CurriculumDivisionTypes.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


