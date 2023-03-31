package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.ConfigValue;
import timetable.api.Teachers;

import java.util.ArrayList;
import java.util.List;

public class TeachersTest {

    @Test
    public void createTest() throws Exception {
        List<Teachers> teachers = new ArrayList<>();

        teachers.add(new Teachers("Иван", "Иванов", "Леопольдович", ""));
        teachers.add(new Teachers("Мария", "Мариева", "Леопольдовна", ""));
        teachers.add(new Teachers("Николай", "Николаев", "Николаевич", ""));
        teachers.add(new Teachers("Светлана", "Курцына", "Петровна", ""));
        teachers.add(new Teachers("Нина", "Котикова", "Константиновна", ""));
        teachers.add(new Teachers("Евдоким", "Иванов", "Евдокимович", ""));
        teachers.add(new Teachers("Софья", "Солнце", "Марсовна", ""));
        teachers.add(new Teachers("Игнат", "Стул", "Александрович", ""));
        teachers.add(new Teachers("Дарья", "Симбухова", "Евгеньевна", ""));
        teachers.add(new Teachers("Дарий", "Симбухов", "Евгеньевич", ""));

        for (Teachers teacher : teachers) {
            int actual = teacher.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            Teachers.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


