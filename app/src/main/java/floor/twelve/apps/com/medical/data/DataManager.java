package floor.twelve.apps.com.medical.data;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import floor.twelve.apps.com.medical.data.local.DbHelper;
import floor.twelve.apps.com.medical.data.local.PreferencesHelper;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.data.remote.RestApi;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

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
}
