package floor.twelve.apps.com.medical.utils;

/**
 * Created by Vrungel on 26.01.2017.
 */

public final class Constants {
  public class Remote {
    public static final String LOCAL = "ru";
    public static final String TERMS_OF_SERVICE_URL = "http://floor12apps.com/terms_of_service";
    public static final String PRIVACY_POLICY_URL = "http://floor12apps.com/privacy_policy";
    private static final String DOMEN = "beauty.api.floor12apps.com";
    public static final String BASE_URL = "https://" + DOMEN + "/";
  }

  public class FragmentTag {
    public static final String MAIN_FRAGMENT = "MainFragment";
  }

  public class Notifications {
    public static final String LAST_BOOKING_ENTITY_ID = "LAST_BOOKING_ENTITY_ID";
    public static final String LAST_BOOKING_ENTITY = "LAST_BOOKING_ENTITY";
    public static final String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";
    public static final String DAILY = "DAILY";
    public static final String HOURLY = "HOURLY";
    public static final String SERVICE = "SERVICE";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
  }

  public class StatusCode {
    public static final int RESPONSE_200 = 200;
    public static final int RESPONSE_204 = 204;
    public static final int RESPONSE_400 = 400;
    public static final int RESPONSE_404 = 404;
    public static final int RESPONSE_503 = 503;
  }

  public class Theme {
    public static final int BLUE = 0;
    public static final int PINK = 1;
    public static final int GREEN = 2;
    public static final int YELLOW = 3;
    public static final int GRAY = 4;
    public static final int PURPLE = 5;
    public static final int RED = 6;
  }

  public class Language {
    public static final String UKRAINIAN = "Українська";
    public static final String RUSSIAN = "Русский";
    public static final String UK = "uk";
    public static final String RU = "ru";
  }

  public class Rotation {
    public static final int PORTRAIT = 2;
    public static final int LANDSCAPE = 3;
  }

  public class RemoteText {
    public static final String BONUS = "bonus";
  }
}
