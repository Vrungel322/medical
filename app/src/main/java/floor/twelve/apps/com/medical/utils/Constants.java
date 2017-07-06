package floor.twelve.apps.com.medical.utils;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class Constants {
  public class Remote {
    private static final String DOMEN = "beauty.api.floor12apps.com";
    public static final String BASE_URL = "https://" + DOMEN + "/";
  }

  public class FragmentTag {
    public static final String MAIN_FRAGMENT = "MainFragment";

  }

  public class FragmentsArgumentKeys {

  }

  public class Notifications {
    public static final String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";
    public static final String DAILY = "DAILY";
    public static final String HOURLY = "HOURLY";
  }

  public class StatusCode {
    public static final int RESPONSE_200 = 200;
    public static final int RESPONSE_204 = 204;
    public static final int RESPONSE_400 = 400;
    public static final int RESPONSE_404 = 404;
  }

  public class Theme {
    public static final int GREEN = 0;
    public static final int BLUE = 1;
    public static final int PINK = 2;
    public static final int YELLOW = 3;
    public static final int GRAY = 4;
    public static final int PURPLE = 5;
    public static final int RED = 6;
  }

  public class Other {
    public static final String MASTER_MALE_PLACEHOLDER = "male";
    public static final String SERVER_ANSWER_EMPTY_STRING = "---";
  }
}
