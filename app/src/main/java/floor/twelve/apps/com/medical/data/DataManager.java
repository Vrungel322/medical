package floor.twelve.apps.com.medical.data;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import floor.twelve.apps.com.medical.data.local.DbHelper;
import floor.twelve.apps.com.medical.data.local.PreferencesHelper;
import floor.twelve.apps.com.medical.data.model.BonusEntity;
import floor.twelve.apps.com.medical.data.model.BonusHistoryEntity;
import floor.twelve.apps.com.medical.data.model.BookingServerEntity;
import floor.twelve.apps.com.medical.data.model.CategoryEntity;
import floor.twelve.apps.com.medical.data.model.DataServiceEntity;
import floor.twelve.apps.com.medical.data.model.DoctorEntity;
import floor.twelve.apps.com.medical.data.model.MasterEntity;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.data.model.OurWorkEntity;
import floor.twelve.apps.com.medical.data.model.PartnerEntity;
import floor.twelve.apps.com.medical.data.model.PhotoGalleryEntity;
import floor.twelve.apps.com.medical.data.model.PhotoWorksEntity;
import floor.twelve.apps.com.medical.data.model.PriceEntity;
import floor.twelve.apps.com.medical.data.model.PricesCategoryEntity;
import floor.twelve.apps.com.medical.data.model.ResultEntity;
import floor.twelve.apps.com.medical.data.model.ReviewEntity;
import floor.twelve.apps.com.medical.data.model.SalesEntity;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import floor.twelve.apps.com.medical.data.model.goods.category.GoodsCategoryEntity;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.data.remote.OfferEntity;
import floor.twelve.apps.com.medical.data.remote.RestApi;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;
import rx.Observable;

import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_LAST_PHONE_FOR_BOOKING;
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

  //ourWorks

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorks() {
    return mRestApi.fetchListOfWorks(mPref.getLanguageCode());
  }

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorksAuth() {
    return mRestApi.fetchListOfWorksAuth(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos() {
    return mRestApi.fetchFavoritePhotos(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  //like/dislike ourWork photo

  public Observable<Response<Void>> addToFavoritePhoto(int photoId) {
    return mRestApi.addToFavoritePhoto(mPref.getLanguageCode(), photoId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(int photoId) {
    return mRestApi.removeFromFavoritePhoto(mPref.getLanguageCode(), photoId,
        mAuthorizationManager.getToken());
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

  //Goods

  public Observable<Response<List<GoodsEntity>>> fetchAllProducts() {
    return mRestApi.fetchAllProducts(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods() {
    return mRestApi.fetchFavoriteGoods(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<GoodsEntity>>> fetchGoodsByCatalogId(Integer id) {
    return mRestApi.fetchGoodsByCatalogId(mPref.getLanguageCode(), id);
  }

  public Observable<Response<List<GoodsCategoryEntity>>> fetchCategories() {
    return mRestApi.fetchCategories(mPref.getLanguageCode());
  }

  //like/dislike goods

  public Observable<Response<Void>> addToFavoriteGoods(int goodsId) {
    return mRestApi.addToFavoriteGoods(mPref.getLanguageCode(), goodsId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoriteGoods(int goodsId) {
    return mRestApi.removeFromFavoriteGoods(mPref.getLanguageCode(), goodsId,
        mAuthorizationManager.getToken());
  }

  ///////////////////////////////////////////////////////////////////////////
  // Mocks
  ///////////////////////////////////////////////////////////////////////////
  public Observable<List<LastBookingEntity>> fetchBookingEntities() {
    List<LastBookingEntity> lastBookingEntities = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      lastBookingEntities.add(
          new LastBookingEntity("МЕД. УСЛУГИ, ПЛАТНЫЕ" + i, "Маникюр, обновление" + i, 140000000,
              "Профессиональная гигиена полости рта ультразвуком", "Партизанский проспект, 48-105",
              "Виктория Алейникова", "Ведущий специалист-стоматолог", 12,
              "http://www.doctorwho.tv/brand/assets/old-brand/img/profile-heros/twelfth-doctor.png",
              "new"));
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

  public Observable<List<SalesEntity>> fetchSales() {
    List<SalesEntity> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(
          new SalesEntity("УЗИ Ультразвуковое (соногарфиЯ) исследование" + i, "30 июня в 15:00",
              "http://www.omagg.com/wp-content/uploads/2017/07/doctor_467179149-56b08aaf3df78cf772cf9262.jpg",
              "25 days", "252", "1921"));
    }
    return Observable.just(list);
  }

  public Observable<List<PricesCategoryEntity>> fetchListOfPricesCategories() {

    ArrayList<PriceEntity> prices = new ArrayList();

    prices.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    prices.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    prices.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    prices.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    prices.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));
    prices.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    prices.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    prices.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    prices.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    prices.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));
    prices.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    prices.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    prices.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    prices.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    prices.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));
    prices.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    prices.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    prices.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    prices.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    prices.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));

    ArrayList<PricesCategoryEntity> list = new ArrayList<>();

    list.add(new PricesCategoryEntity("1", "Лазерная пластическая хирургия", prices));
    list.add(new PricesCategoryEntity("2", "Пластическая хирургия", prices));
    list.add(new PricesCategoryEntity("3", "Маммопластика", prices));
    list.add(new PricesCategoryEntity("4", "Лазерная дерматология", prices));
    list.add(new PricesCategoryEntity("5", "Лазерное омоложение кожи лица", prices));
    list.add(new PricesCategoryEntity("6", "Лазерная эпиляция", prices));
    list.add(new PricesCategoryEntity("7", "Инъекции", prices));

    return Observable.just(list);
  }

  public Observable<List<PriceEntity>> fetchPriceByCategory() {

    ArrayList<PriceEntity> list = new ArrayList();

    list.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    list.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    list.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    list.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    list.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));
    list.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    list.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    list.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    list.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    list.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));
    list.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    list.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    list.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    list.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    list.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));
    list.add(new PriceEntity("1", "Установка коронки", "200 грн"));
    list.add(new PriceEntity("2", "Установка брекетов", "300 грн"));
    list.add(new PriceEntity("3", "Протезирование зубов", "400 грн"));
    list.add(new PriceEntity("4", "Имплантация зубов", "500 грн"));
    list.add(new PriceEntity("5", "Отбеливание зубов", "600 грн"));

    return Observable.just(list);
  }

  public Observable<List<PhotoGalleryEntity>> fetchGalleries() {
    List<PhotoGalleryEntity> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      list.add(new PhotoGalleryEntity("Наш медицинский персонал" + i,
          "https://medaboutme.ru/upload/resize_cache/iblock/e2b/940_540_1/chto_lechit_vrach_kombustiolog.jpg",
          "10"));
    }
    return Observable.just(list);
  }

  public Observable<List<DoctorEntity>> fetchAllDoctors() {
    ArrayList<DoctorEntity> doctorEntities = new ArrayList<>();

    doctorEntities.add(new DoctorEntity("1", "", "Барановская\n" + "Ирина Аркадьевна",
        "Травматолог-ортопед детский", "1 категория, профессор", 3, 38));
    doctorEntities.add(new DoctorEntity("1", "", "Барановская\n" + "Ирина Аркадьевна",
        "Травматолог-ортопед детский", "1 категория, профессор", 3, 38));
    doctorEntities.add(new DoctorEntity("1", "", "Барановская\n" + "Ирина Аркадьевна",
        "Травматолог-ортопед детский", "1 категория, профессор", 3, 38));

    return Observable.just(doctorEntities);
  }

  public Observable<List<ServiceEntity>> fetchDoctorsServices() {
    ArrayList<ServiceEntity> serviceEntities = new ArrayList<>();

    serviceEntities.add(new ServiceEntity(1, "Консультация стоматолога",
        "Профессиональная гигиена полости рта ультразвуком. Первое посещение врача-стоматолога может быть профилактического, ознакомительного характера",
        "", "", "200", "", "", false, false));
    serviceEntities.add(new ServiceEntity(1, "Отбеливание зубов",
        "Профессиональная гигиена полости рта ультразвуком. Первое посещение врача-стоматолога может быть профилактического, ознакомительного характера",
        "", "", "200", "", "", false, false));
    serviceEntities.add(new ServiceEntity(1, "Детский стоматолог",
        "Профессиональная гигиена полости рта ультразвуком. Первое посещение врача-стоматолога может быть профилактического, ознакомительного характера",
        "", "", "200", "", "", false, false));
    serviceEntities.add(new ServiceEntity(1, "Професиональная гигиена",
        "Профессиональная гигиена полости рта ультразвуком. Первое посещение врача-стоматолога может быть профилактического, ознакомительного характера",
        "", "", "200", "", "", false, false));

    return Observable.just(serviceEntities);
  }

  public Observable<List<ReviewEntity>> fetchDoctorsReviews() {
    ArrayList<ReviewEntity> list = new ArrayList<>();

    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));
    list.add(new ReviewEntity("1", "Ирина П.", "Вчера",
        "Спасибо огромное очень внимательному врачу Андреевой Надежде Леонидовне!", "3", ""));

    return Observable.just(list);
  }

  public Observable<List<MasterEntity>> fetchMasterForTest() {
    ArrayList<MasterEntity> list = new ArrayList<>();

    list.add(new MasterEntity("1", "Василий Петровчи", "", "Массажист", "male", 3));
    list.add(new MasterEntity("1", "Василий Петровчи", "", "Массажист", "male", 3));
    list.add(new MasterEntity("1", "Василий Петровчи", "", "Массажист", "male", 3));
    list.add(new MasterEntity("1", "Василий Петровчи", "", "Массажист", "male", 3));
    list.add(new MasterEntity("1", "Василий Петровчи", "", "Массажист", "male", 3));

    return Observable.just(list);
  }

  //user

  public String getLastPhoneForBooking() {
    return mAuthorizationManager.getAdditionalField(PREF_LAST_PHONE_FOR_BOOKING,
        mPref.getLastPhoneForBooking());
  }

  public void setLastPhoneForBooking(String lastPhone) {
    mPref.setLastPhoneForBooking(lastPhone);
  }

  //checkin service.

  public Observable<Response<List<CategoryEntity>>> fetchCategory() {
    return mRestApi.fetchCategory(mPref.getLanguageCode());
  }

  public Observable<Response<List<ServiceEntity>>> fetchAllServices() {
    return mRestApi.fetchAllServices(mPref.getLanguageCode());
  }

  public Observable<Response<List<ServiceEntity>>> fetchAllServicesByMasterId(String masterId) {
    return mRestApi.fetchAllServicesByMasterId(mPref.getLanguageCode(), masterId);
  }

  public Observable<Response<List<ServiceEntity>>> fetchServicesOfCategoryWithId(int id) {
    return mRestApi.fetchServicesOfCategoryWithId(mPref.getLanguageCode(), id);
  }

  public Observable<Response<List<CategoryEntity>>> fetchCategoriesOfCategoryWithId(int parentId) {
    return mRestApi.fetchCategoriesOfCategoryWithId(mPref.getLanguageCode(), parentId);
  }

  public Observable<Response<List<DataServiceEntity>>> fetchDaysData(String serviceId) {
    return mRestApi.fetchDaysData(mPref.getLanguageCode(), serviceId);
  }

  public Observable<Response<List<MasterEntity>>> fetchMasters(String serviceId, String dataID) {
    return mRestApi.fetchMasters(mPref.getLanguageCode(), serviceId, dataID);
  }

  public Observable<Response<List<MasterEntity>>> fetchAllMasters() {
    return mRestApi.fetchAllMasters(mPref.getLanguageCode());
  }

  public Observable<Response<List<DataServiceEntity>>> fetchDaysDataWithMasterId(String masterId) {
    return mRestApi.fetchDaysDataWithMasterId(mPref.getLanguageCode(), masterId);
  }

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(
      BookingServerEntity bookingServerEntity) {
    return mRestApi.checkInService(mPref.getLanguageCode(), mAuthorizationManager.getToken(),
        bookingServerEntity);
  }

  //main screen

  public Observable<Response<List<LastBookingEntity>>> fetchLastBooking() {
    return mRestApi.fetchLastBooking(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<LastBookingEntity>>> fetchLastBookingHistory() {
    return mRestApi.fetchLastBookingHistory(mPref.getLanguageCode(),
        mAuthorizationManager.getToken());
  }

  public Observable<retrofit2.Response<Void>> cancelOrder(Integer serviceId) {
    return mRestApi.cancelOrder(mPref.getLanguageCode(), serviceId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> postponeService(String entryId, int scheduleId) {
    return mRestApi.postponeService(mPref.getLanguageCode(), entryId,
        mAuthorizationManager.getToken(), scheduleId);
  }

  //bonus

  public Observable<Response<BonusEntity>> fetchBonusCount() {
    return mRestApi.fetchBonusCount(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<BonusHistoryEntity>>> fetchBonusHistory() {
    return mRestApi.fetchBonusHistory(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public void setBonusCount(int bonusCount) {
    mPref.setBonusCount(bonusCount);
  }

  public int getBonusCountInt() {
    return mPref.getBonusCountInt();
  }

  public Observable<Integer> getBonusCountObservable() {
    return mPref.getBonusCountObservable();
  }
}
