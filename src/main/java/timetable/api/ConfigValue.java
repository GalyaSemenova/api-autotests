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

  public static int CODE_200 = 200;
  public static int CODE_201 = 201;

  public static int CODE_400 = 400;

  public static int CODE_500 = 500;
}
