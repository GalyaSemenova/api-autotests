package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;

public class BellGridTest {

    @Test
    public void createTest() throws Exception {
        List<BellGrid> bellGrids = new ArrayList<>();

        bellGrids.add(new BellGrid("Сред школа первая смена"));
        bellGrids.add(new BellGrid("Сред школа вторая смена"));
        bellGrids.add(new BellGrid("Начальная школа"));

        for (BellGrid bellGrid : bellGrids) {
            int actual = bellGrid.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            BellGrid.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


