package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;

public class CurriculumDivisionSubgroupsTest {
    @Test
    public void createTest() throws Exception {
        CurriculumDivisionTypes.getAll();

        List<CurriculumDivisionSubgroups> subgroups = new ArrayList<>();
        subgroups.add(new CurriculumDivisionSubgroups("муж", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.get(0), 1));
        subgroups.add(new CurriculumDivisionSubgroups("жен", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.get(0), 2));
        subgroups.add(new CurriculumDivisionSubgroups("подгруппа 1", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.get(1), 1));
        subgroups.add(new CurriculumDivisionSubgroups("подгруппа 2", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.get(1), 2));
        subgroups.add(new CurriculumDivisionSubgroups("подгруппа 3", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.get(1), 3));
        subgroups.add(new CurriculumDivisionSubgroups("продвинутый", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.get(2), 1));
        subgroups.add(new CurriculumDivisionSubgroups("базовый", ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.get(2), 2));

        for (CurriculumDivisionSubgroups subgroup : subgroups) {
            int actual = subgroup.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            CurriculumDivisionSubgroups.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


