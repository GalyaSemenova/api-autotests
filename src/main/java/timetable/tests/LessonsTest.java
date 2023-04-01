package timetable.tests;

import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LessonsTest {
    /*
        {
            "id": 1,                    1
            "subject_id": 1,            Рус яз - subgroupTeachers[3]
            "group_id": 1,              "а" - subgroupTeachers[0]
            "division_type_id": 1,      по полу - subgroupTeachers[1]
            "subgroup_num": 1,          муж - subgroupTeachers[2]
            "timetable_id": 1,          Расписание 2023 - timetables
            "classroom_id": 1,          Каб 100 - classrooms
            "teacher_id" :1,            Иванов И.Л. - subgroupTeachers[4]
            "bell_grid_id": 1,          Сред школа 1я смена - intervals[0]
            "week_num": 1,              1я нед  - intervals[1]
            "day_num": 1,               - intervals[2]
            "num_on_day": 1             - intervals[3]
        }
     */

    @Test
    public void createTest() throws Exception {
        Random rand = new Random();

        Timetables.getAll();
        Classroom.getAll();
        Intervals.getAll();
        SubgroupTeachers.getAll();

        List<Lessons> lessons = new ArrayList<>();
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 1, 1, 2, 1, 1, 1));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 1, 1, 2, 1, 1, 2));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 2, 2, 2, 1, 1, 3));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 2, 2, 2, 1, 1, 4));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 1, 1, 5));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 1, 1, 6));

        lessons.add(new Lessons(7, 1 , 1, 4, 1, 1, 4, 2, 1, 2, 1));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 1, 1, 2, 1, 2, 2));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 2, 5, 2, 1, 2, 3));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 2, 2, 2, 1, 2, 4));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 1, 2, 5));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 1, 2, 6));

        lessons.add(new Lessons(7, 1 , 1, 3, 1, 1, 3, 2, 1, 3, 1));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 1, 2, 2, 1, 3, 2));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 2, 1, 2, 1, 3, 3));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 2, 3, 2, 1, 3, 4));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 3, 2, 2, 1, 3, 5));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 3, 1, 2, 1, 3, 6));

        lessons.add(new Lessons(7, 1 , 1, 1, 1, 1, 1, 2, 1, 4, 1));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 1, 1, 2, 1, 4, 2));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 2, 2, 2, 1, 4, 3));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 2, 2, 2, 1, 4, 4));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 1, 4, 5));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 1, 4, 6));

        lessons.add(new Lessons(7, 1 , 1, 5, 1, 1, 5, 2, 1, 5, 1));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 1, 5, 2, 1, 5, 2));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 2, 5, 2, 1, 5, 3));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 2, 5, 2, 1, 5, 4));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 3, 5, 2, 1, 5, 5));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 3, 5, 2, 1, 5, 6));

        //-------------------------------------

        lessons.add(new Lessons(7, 1 , 1, 4, 1, 1, 4, 2, 2, 1, 1));
        lessons.add(new Lessons(7, 1 , 1, 4, 1, 1, 4, 2, 2, 1, 2));
        lessons.add(new Lessons(7, 1 , 1, 4, 1, 2, 4, 2, 2, 1, 3));
        lessons.add(new Lessons(7, 1 , 1, 4, 1, 2, 4, 2, 2, 1, 4));
        lessons.add(new Lessons(7, 1 , 1, 4, 1, 3, 4, 2, 2, 1, 5));
        lessons.add(new Lessons(7, 1 , 1, 4, 1, 3, 4, 2, 2, 1, 6));

        lessons.add(new Lessons(7, 1 , 1, 3, 1, 1, 3, 2, 2, 2, 1));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 1, 3, 2, 2, 2, 2));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 2, 3, 2, 2, 2, 3));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 2, 3, 2, 2, 2, 4));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 2, 2, 5));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 2, 2, 6));

        lessons.add(new Lessons(7, 1 , 1, 3, 1, 1, 3, 2, 2, 3, 1));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 1, 2, 2, 2, 3, 2));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 2, 1, 2, 2, 3, 3));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 2, 3, 2, 2, 3, 4));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 3, 2, 2, 2, 3, 5));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 3, 1, 2, 2, 3, 6));

        lessons.add(new Lessons(7, 1 , 1, 1, 1, 1, 1, 2, 2, 4, 1));
        lessons.add(new Lessons(7, 1 , 1, 1, 1, 1, 1, 2, 2, 4, 2));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 2, 2, 2, 2, 4, 3));
        lessons.add(new Lessons(7, 1 , 1, 2, 1, 2, 2, 2, 2, 4, 4));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 2, 4, 5));
        lessons.add(new Lessons(7, 1 , 1, 3, 1, 3, 3, 2, 2, 4, 6));

        lessons.add(new Lessons(7, 1 , 1, 5, 1, 1, 5, 2, 2, 5, 1));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 1, 5, 2, 2, 5, 2));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 2, 5, 2, 2, 5, 3));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 2, 5, 2, 2, 5, 4));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 3, 5, 2, 2, 5, 5));
        lessons.add(new Lessons(7, 1 , 1, 5, 1, 3, 5, 2, 2, 5, 6));

        for (Lessons lesson : lessons) {
            int actual = lesson.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }

    }

    @Test
    public void deleteTest() {
        try {
            Lessons.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


