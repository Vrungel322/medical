package floor.twelve.apps.com.medical.data;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import floor.twelve.apps.com.medical.data.local.DbHelper;
import floor.twelve.apps.com.medical.data.local.PreferencesHelper;
import floor.twelve.apps.com.medical.data.model.ResultEntity;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.data.remote.RestApi;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

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

  //language
  public String getSelectedLanguage() {
    return mPref.getSelectedLanguage();
  }

  public void setSelectedLanguage(String selectedLanguage) {
    mPref.setSelectedLanguage(selectedLanguage);
  }

  ///////////////////////////////////////////////////////////////////////////
  // Mocks
  ///////////////////////////////////////////////////////////////////////////
  public Observable<List<LastBookingEntity>> fetchBookingEntities() {
    List<LastBookingEntity> lastBookingEntities = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      lastBookingEntities.add(
          new LastBookingEntity("МЕД. УСЛУГИ, ПЛАТНЫЕ" + i, "Маникюр, обновление" + i, "14:00 pm",
              "Профессиональная гигиена полости рта ультразвуком", "Партизанский проспект, 48-105",
              "Виктория Алейникова", "Ведущий специалист-стоматолог", 12,
              "http://www.doctorwho.tv/brand/assets/old-brand/img/profile-heros/twelfth-doctor.png",1));
    }
    return Observable.just(lastBookingEntities);
  }

  public Observable<List<ResultEntity>> fetchResultEntities() {
    List<ResultEntity> resultEntities = new ArrayList<>();

    resultEntities.add(new ResultEntity("1", "Анализ крови", "Сдача анализа крови", "14.02.2017"));
    resultEntities.add(new ResultEntity("1", "Анализ кала", "Сдача анализа кала", "15.02.2017"));
    resultEntities.add(new ResultEntity("1", "Анализ мочи", "Сдача анализа мочи", "16.02.2017"));
    resultEntities.add(new ResultEntity("1", "Обледование", "Дата обследования", "17.02.2017"));
    resultEntities.add(new ResultEntity("1", "МРТ", "Дата обследования", "18.02.2017"));
    resultEntities.add(new ResultEntity("1", "Узи", "Дата обследования", "19.02.2017"));

    return Observable.just(resultEntities);
  }
}
