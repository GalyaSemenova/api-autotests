package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.ConfigValue;
import timetable.api.Subjects;

import java.util.ArrayList;
import java.util.List;

public class SubjectsTest {

    @Test
    public void createTest() throws Exception {
        List<Subjects> subjects = new ArrayList<>();

        subjects.add(new Subjects("Русский язык", "Рус яз", true));
        subjects.add(new Subjects("Математика", "Матем", true));
        subjects.add(new Subjects("Алгебра", "Алг", true));
        subjects.add(new Subjects("Информатика", "Инф", true));
        subjects.add(new Subjects("Рисование", "Рис", false));

        for (Subjects subject : subjects) {
            int actual = subject.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }
    }

    @Test
    public void deleteTest() {
        try {
            Subjects.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


