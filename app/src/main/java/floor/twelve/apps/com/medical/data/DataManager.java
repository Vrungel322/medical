package floor.twelve.apps.com.medical.data;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import floor.twelve.apps.com.medical.data.local.DbHelper;
import floor.twelve.apps.com.medical.data.local.PreferencesHelper;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.data.model.PartnerEntity;
import floor.twelve.apps.com.medical.data.model.ResultEntity;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.data.remote.OfferEntity;
import floor.twelve.apps.com.medical.data.remote.RestApi;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;
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

  //News
  public Observable<Response<List<NewsEntity>>> fetchAllNews() {
    return mRestApi.fetchAllNews(mPref.getLanguageCode());
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
              "http://www.doctorwho.tv/brand/assets/old-brand/img/profile-heros/twelfth-doctor.png",
              1));
    }
    return Observable.just(lastBookingEntities);
  }

  public Observable<List<OfferEntity>> fetchOfferEntities() {
    List<OfferEntity> offerEntities = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      offerEntities.add(new OfferEntity("http://lady1.net/images/%D1%80%D0%B8%D1%81.3_3.jpg",
          "УЗИ Ультразвуковое (соногарфия) исследование" + i, "Исследование организма человека" + i,
          "961", "2547", "25 days"));
    }
    return Observable.just(offerEntities);
  }

  public Observable<List<ResultEntity>> fetchResultEntities() {
    List<ResultEntity> resultEntities = new ArrayList<>();

    resultEntities.add(new ResultEntity("1", "Анализ крови", "Сдача анализа крови", "14.02.2017"));
    resultEntities.add(new ResultEntity("2", "Анализ кала", "Сдача анализа кала", "15.02.2017"));
    resultEntities.add(new ResultEntity("3", "Анализ мочи", "Сдача анализа мочи", "16.02.2017"));
    resultEntities.add(new ResultEntity("4", "Обледование", "Дата обследования", "17.02.2017"));
    resultEntities.add(new ResultEntity("5", "МРТ", "Дата обследования", "18.02.2017"));
    resultEntities.add(new ResultEntity("6", "Узи", "Дата обследования", "19.02.2017"));

    return Observable.just(resultEntities);
  }

  public Observable<List<ResultEntity>> fetchLastResultEntities() {
    List<ResultEntity> resultEntities = new ArrayList<>();

    resultEntities.add(new ResultEntity("1", "Анализ кала", "Сдача анализа кала", "15.02.2017"));
    resultEntities.add(new ResultEntity("2", "МРТ", "Дата обследования", "18.02.2017"));
    resultEntities.add(new ResultEntity("3", "Узи", "Дата обследования", "19.02.2017"));

    return Observable.just(resultEntities);
  }

  public Observable<List<PartnerEntity>> fetchListOfPartners() {
    ArrayList<PartnerEntity> partnerEntities = new ArrayList<>();

    ArrayList<String> addresses = new ArrayList<>();
    addresses.add("620 Eighth Avenue New York, NY 10018");

    ArrayList<String> phones = new ArrayList<>();
    phones.add("+380551234455");
    phones.add("+380551234456");

    ArrayList<String> emails = new ArrayList<>();
    emails.add("floor12@gmail.com");

    ArrayList<String> links = new ArrayList<>();
    links.add("https://www.floor12apps.com");
    links.add("https://www.floor12apps.com");

    partnerEntities.add(new PartnerEntity("1", "Партнер1", addresses, phones, emails, links));
    partnerEntities.add(new PartnerEntity("2", "Партнер2", addresses, phones, emails, links));
    partnerEntities.add(new PartnerEntity("3", "Партнер3", addresses, phones, emails, links));
    partnerEntities.add(new PartnerEntity("4", "Партнер4", addresses, phones, emails, links));
    partnerEntities.add(new PartnerEntity("5", "Партнер5", addresses, phones, emails, links));

    return Observable.just(partnerEntities);
  }
}
