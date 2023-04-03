package timetable.tests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import timetable.api.*;

import java.util.*;

public class PlacedLessonsTest {
    private static final long DAY_IN_SECONDS = 86400L; // Количество секунд в одном дне
    private static final long LESSON_DURATION = 45L * 60L; // Продолжительность урока в секундах
    private static int LESSONS_IN_DAY; // Количество уроков в одном дне

    static List<PlacedLessons> placedLessons;

    // Генерирует Unix время начала и окончания уроков для заданного дня недели
    private static PlacedLessons generateLessonsForDay(int dayOfWeek, long startOfWeek) {
        Subjects.getAll();
        var numSubjects = ConfigValue.SUBJECTS_LIST.size();

        Teachers.getAll();
        var numTeachers = ConfigValue.TEACHERS_LIST.size();

        Classroom.getAll();
        var numClassrooms = ConfigValue.CLASSROOMS_LIST.size();

        Group.getAll();
        var numGroups = ConfigValue.GROUPS_LIST.size();

        CurriculumDivisionTypes.getAll();
        var numTypes = ConfigValue.CURRICULUM_DIVISION_TYPES_LIST.size();

        CurriculumDivisionSubgroups.getAll();
        var numSubgroups = ConfigValue.CURRICULUM_DIVISION_SUBGROUPS_LIST.size();


        Random rand = new Random();
        LESSONS_IN_DAY = rand.nextInt(3) + 4;

        PlacedLessons placedLesson;

        long startOfDay = startOfWeek + (dayOfWeek - 1) * DAY_IN_SECONDS;
        long currentLessonStart = startOfDay;

        for (int lessonNum = 1; lessonNum <= LESSONS_IN_DAY; lessonNum++) {
            long currentLessonEnd = currentLessonStart + LESSON_DURATION;
            placedLesson = new PlacedLessons(currentLessonStart, currentLessonEnd,
                    rand.nextInt(numSubjects) + 1,
                    " ",
                    rand.nextInt(numTeachers) + 1,
                    rand.nextInt(numClassrooms) + 1,
                    Integer.parseInt(ConfigValue.GROUPS_LIST.get(rand.nextInt(numTypes)).split(" ")[0]),
                    rand.nextInt(numTypes) + 1,
                    1,
                    false,
                    false,
                    false
            );
            placedLessons.add(placedLesson);
            currentLessonStart = currentLessonEnd; // Начало следующего урока
        }
        return null;
    }


    @Test
    public void createTest() throws Exception {
        placedLessons = new ArrayList<>();
        Calendar cal = Calendar.getInstance(); // создаем объект Calendar для текущей даты
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()); // устанавливаем день недели на первый день недели
        cal.set(Calendar.HOUR_OF_DAY, 0); // устанавливаем часы на 0
        cal.set(Calendar.MINUTE, 0); // устанавливаем минуты на 0
        cal.set(Calendar.SECOND, 0); // устанавливаем секунды на 0
        cal.set(Calendar.MILLISECOND, 0); // устанавливаем миллисекунды на 0
        long startOfWeek = cal.getTimeInMillis() / 1000; // получаем unix time в секундах
        int daysInWeek = 6; // Количество дней в неделе

        // Генерируем расписание для каждого дня недели
        for (int dayOfWeek = 1; dayOfWeek <= daysInWeek; dayOfWeek++) {
            generateLessonsForDay(dayOfWeek, startOfWeek);
        }


        for (PlacedLessons placedLesson : placedLessons) {
            int actual = placedLesson.create().statusCode();
            Assert.assertEquals(ConfigValue.CODE_201, actual);
        }

    }

    @Test
    public void deleteTest() {
        try {
            PlacedLessons.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


