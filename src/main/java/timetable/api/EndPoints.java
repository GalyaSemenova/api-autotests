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
  public static final String LOGOUT_URL = BASE_URL + "/api/sessions/logout";
  public static final String WHO_URL = BASE_URL + "/api/sessions/whoami";

  public static final String CLASSROOM_TYPE_URL = BASE_URL + "/api/classroom/types";
  public static final String CLASSROOM_URL = BASE_URL + "/api/classroom/classrooms";
  public static final String CLASSROOM_SELECTED_TYPES_URL = BASE_URL + "/api/classroom/selected_types";

}
