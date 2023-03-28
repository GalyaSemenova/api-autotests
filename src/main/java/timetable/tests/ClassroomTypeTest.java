package timetable.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import timetable.api.Auth;
import timetable.api.ClassroomType;
import timetable.api.ConfigValue;

import java.util.ArrayList;
import java.util.List;

public class ClassroomTypeTest {

    @Test
    public void createTest() {
        List<ClassroomType> types = new ArrayList<>();
        types.add(new ClassroomType("большой спортзал"));
        types.add(new ClassroomType("малый спортзал"));
        types.add(new ClassroomType("хореография"));
        types.add(new ClassroomType("рисование"));
        types.add(new ClassroomType("каб информатики"));
        types.add(new ClassroomType("обычный каб"));
        for (ClassroomType type : types) {
            int actual = type.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            ClassroomType.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


