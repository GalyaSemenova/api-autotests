package timetable.api;

import java.util.ArrayList;
import java.util.List;

/**
 * The class with configuration global values.
 */
public final class ConfigValue {
  private ConfigValue() {
  }


  public static boolean AUTH = false;
  public static List<Integer> CLASSROOM_TYPES_LIST = new ArrayList<>();
  public static List<Integer> CLASSROOMS_LIST = new ArrayList<>();
  public static List<Integer> BELL_GRIDS_LIST = new ArrayList<>();
  public static List<String> WEEKS_LIST = new ArrayList<>();
  public static List<String> INTERVALS_LIST = new ArrayList<>();
  public static List<Integer> SUBJECTS_LIST = new ArrayList<>();
  public static List<String> ALLOWED_CLASSROOM_TYPES_LIST = new ArrayList<>();
  public static List<String> DIFFICULTY_POINTS_LIST = new ArrayList<>();
  public static List<Integer> CURRICULUM_DIVISION_TYPES_LIST = new ArrayList<>();
  public static List<String> CURRICULUM_DIVISION_SUBGROUPS_LIST = new ArrayList<>();
  public static List<Integer> TEACHERS_LIST = new ArrayList<>();
  public static List<String> TEACHER_PRIORITY_CLASSROOMS_LIST = new ArrayList<>();
  public static List<Integer> ANNUAL_CURRICULUMS_LIST = new ArrayList<>();
  public static List<String> ACADEMIC_HOURS_LIST = new ArrayList<>();
  public static List<String> GROUPS_LIST = new ArrayList<>();
  public static List<String> GROUPS_PRIORITY_CLASSROOMS_LIST = new ArrayList<>();
  public static List<String> SUBGROUP_TEACHERS_LIST = new ArrayList<>();
  public static List<Integer> TIMETABLES_LIST = new ArrayList<>();
  public static List<String> LESSONS_LIST = new ArrayList<>();
  public static List<String> PLACED_LESSONS_LIST = new ArrayList<>();

  public static int CODE_200 = 200;
  public static int CODE_201 = 201;
}
