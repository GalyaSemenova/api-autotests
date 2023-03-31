package timetable.api;

/**
 * The class with general urls for actions with courses.
 */
public final class EndPoints {
  /**
   * The private constructor of class.
   */
  private EndPoints() {
  }

  private static final String BASE_URL = "http://192.168.1.78";

  public static final String LOGIN_URL = BASE_URL + "/api/sessions/login";
  public static final String CLASSROOM_TYPE_URL = BASE_URL + "/api/classroom/types";
  public static final String CLASSROOM_URL = BASE_URL + "/api/classroom/classrooms";
  public static final String CLASSROOM_SELECTED_TYPES_URL = BASE_URL + "/api/classroom/selected_types";
  public static final String BELL_GRID_URL = BASE_URL + "/api/bell_grid/bell_grids";
  public static final String WEEKS_URL = BASE_URL + "/api/bell_grid/weeks";
  public static final String INTERVALS_URL = BASE_URL + "/api/bell_grid/intervals";
  public static final String SUBJECTS_URL = BASE_URL + "/api/subject/subjects";
  public static final String ALLOWED_CLASSROOM_TYPES_URL = BASE_URL + "/api/subject/allowed_classroom_types";
  public static final String DIFFICULTY_POINTS_URL = BASE_URL + "/api/subject/difficulty_points";
  public static final String CURRICULUM_DIVISION_TYPES_URL = BASE_URL + "/api/curriculum/division_types";
  public static final String CURRICULUM_DIVISION_SUBGROUPS_URL = BASE_URL + "/api/curriculum/division_subgroups";
  public static final String TEACHERS_URL = BASE_URL + "/api/teacher/teachers";
  public static final String TEACHER_PRIORITY_CLASSROOMS_URL = BASE_URL + "/api/teacher/priority_classrooms";
  public static final String ANNUAL_CURRICULUMS_URL = BASE_URL + "/api/curriculum/annual_curriculums";
  public static final String ACADEMIC_HOURS_URL = BASE_URL + "/api/curriculum/academic_hours";
  public static final String GROUPS_URL = BASE_URL + "/api/group/groups";
  public static final String GROUPS_PRIORITY_CLASSROOMS_URL = BASE_URL + "/api/group/priority_classrooms";
  public static final String SUBGROUP_TEACHERS_URL = BASE_URL + "/api/group/subgroup_teachers";

}
