package floor.twelve.apps.com.medical.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "com.medical.floor";

  public static final String PREF_THEME_SELECTED = "PREF_THEME_SELECTED";
  public static final String PREF_LANGUAGE_CODE = "PREF_LANGUAGE_CODE";

  //notifications
  public static final String PREF_NOTIF_HOURLY_ENABLED = "PREF_NOTIF_HOURLY_ENABLED";
  public static final String PREF_NOTIF_DAILY_ENABLED = "PREF_NOTIF_DAILY_ENABLED";
  public static final String PREF_NOTIF_HOURS = "PREF_NOTIF_HOURS";
  public static final String PREF_NOTIF_HOURS_NIGHT_START = "PREF_NOTIF_HOURS_NIGHT_START";
  public static final String PREF_NOTIF_HOURS_NIGHT_END = "PREF_NOTIF_HOURS_NIGHT_END";
  public static final String PREF_NOTIF_DAYS = "PREF_NOTIF_DAYS";
  public static final String PREF_NOTIF_NIGHT_MODE = "PREF_NOTIF_NIGHT_MODE";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public int getThemeSelected() {
    return mPreferences.getInt(PREF_THEME_SELECTED, 0);
  }

  public void setThemeSelected(int themeSelected) {
    mPreferences.edit().putInt(PREF_THEME_SELECTED, themeSelected).apply();
  }

  public void logoutUser() {
    clear();
  }

  ///////////////////////////////////////////////////////////////////////////
  // notifications
  ///////////////////////////////////////////////////////////////////////////

  public boolean isHourlyNotificationsEnabled() {
    return mPreferences.getBoolean(PREF_NOTIF_HOURLY_ENABLED, true);
  }

  public void setHourlyNotificationsEnabled(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_HOURLY_ENABLED, enabled).apply();
  }

  public boolean isDailyNotificationsEnabled() {
    return mPreferences.getBoolean(PREF_NOTIF_DAILY_ENABLED, true);
  }

  public void setDailyNotificationsEnabled(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_DAILY_ENABLED, enabled).apply();
  }

  public int getNotificationDays() {
    return mPreferences.getInt(PREF_NOTIF_DAYS, 1);
  }

  public void setNotificationDays(int days) {
    mPreferences.edit().putInt(PREF_NOTIF_DAYS, days).apply();
  }

  public long getNotificationHours() {
    return mPreferences.getLong(PREF_NOTIF_HOURS, 3600000);
  }

  public long getNotificationHoursNightStart() {
    return mPreferences.getLong(PREF_NOTIF_HOURS_NIGHT_START, 82800000);
  }

  public long getNotificationHoursNightEnd() {
    return mPreferences.getLong(PREF_NOTIF_HOURS_NIGHT_END, 25200000);
  }

  public void setNotificationHours(long millis) {
    mPreferences.edit().putLong(PREF_NOTIF_HOURS, millis).apply();
  }

  public void setNotificationHoursNightStart(long millis) {
    mPreferences.edit().putLong(PREF_NOTIF_HOURS_NIGHT_START, millis).apply();
  }

  public void setNotificationHoursNightEnd(long millis) {
    mPreferences.edit().putLong(PREF_NOTIF_HOURS_NIGHT_END, millis).apply();
  }

  public boolean isNightMode() {
    return mPreferences.getBoolean(PREF_NOTIF_NIGHT_MODE, true);
  }

  public void setNightMode(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_NIGHT_MODE, enabled).apply();
  }
}
