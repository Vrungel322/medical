package floor.twelve.apps.com.medical.data;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import floor.twelve.apps.com.medical.data.local.DbHelper;
import floor.twelve.apps.com.medical.data.local.PreferencesHelper;
import floor.twelve.apps.com.medical.data.remote.RestApi;
import io.realm.RealmObject;
import java.util.List;

import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_NOTIF_DAILY_ENABLED;
import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_NOTIF_DAYS;
import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_NOTIF_HOURLY_ENABLED;
import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_NOTIF_HOURS;
import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_NOTIF_HOURS_NIGHT_END;
import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_NOTIF_HOURS_NIGHT_START;
import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_NOTIF_NIGHT_MODE;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class DataManager {

  private DbHelper mDbHelper;
  private RestApi mRestApi;
  private PreferencesHelper mPref;
  private AuthorizationManager mAuthorizationManager;

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper,
      AuthorizationManager authorizationManager, DbHelper dbHelper) {
    this.mRestApi = restApi;
    this.mPref = preferencesHelper;
    this.mAuthorizationManager = authorizationManager;
    this.mDbHelper = dbHelper;
  }

  //db
  public <T extends RealmObject> void saveObjToDb(T object) {
    mDbHelper.save(object);
  }

  public <T extends RealmObject> List<T> getAllElementsFromDB(Class<T> clazz) {
    return mDbHelper.getAll(clazz);
  }

  public <T extends RealmObject> List<T> getElementsFromDBByQuery(Class<T> clazz, String field,
      String value) {
    return mDbHelper.getElementsFromDBByQuery(clazz, field, value);
  }

  public <T extends RealmObject> T getElementById(Class<T> clazz, int id) {
    return mDbHelper.getElementById(clazz, id);
  }

  public <T extends RealmObject> void dropRealmTable(Class<T> clazz) {
    mDbHelper.dropRealmTable(clazz);
  }

  //authorization module

  public void logoutUser() {
    mPref.logoutUser();
    mAuthorizationManager.clear();
  }

  //Notification

  public boolean isHourlyNotificationsEnabled() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURLY_ENABLED, true);
  }

  public void setHourlyNotificationsEnabled(boolean enabled) {
    mPref.setHourlyNotificationsEnabled(enabled);
  }

  public boolean isDailyNotificationsEnabled() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_DAILY_ENABLED, true);
  }

  public void setDailyNotificationsEnabled(boolean enabled) {
    mPref.setDailyNotificationsEnabled(enabled);
  }

  public int getNotificationDays() {
    return (int) mAuthorizationManager.getAdditionalField(PREF_NOTIF_DAYS, 1);
  }

  public void setNotificationDays(int days) {
    mPref.setNotificationDays(days);
  }

  public long getNotificationHours() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS, 3600000);
  }

  public void setNotificationHours(long millis) {
    mPref.setNotificationHours(millis);
  }

  public void setNotificationHoursNightStart(long millis) {
    mPref.setNotificationHoursNightStart(millis);
  }

  public void setNotificationHoursNightEnd(long millis) {
    mPref.setNotificationHoursNightEnd(millis);
  }

  public long getNotificationHoursNightStart() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS_NIGHT_START, 82800000);
  }

  public long getNotificationHoursNightEnd() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS_NIGHT_END, 25200000);
  }

  public boolean isNightMode() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_NIGHT_MODE, true);
  }

  public void setNightMode(boolean enabled) {
    mPref.setNightMode(enabled);
  }

  //theme

  public void setThemeSelected(int themeSelected) {
    mPref.setThemeSelected(themeSelected);
  }

  public int getThemeSelected() {
    return mPref.getThemeSelected();
  }
}
