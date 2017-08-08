package floor.twelve.apps.com.medical.di.modules;

import android.content.Context;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import dagger.Module;
import dagger.Provides;
import floor.twelve.apps.com.medical.data.DataManager;
import floor.twelve.apps.com.medical.data.local.DbHelper;
import floor.twelve.apps.com.medical.data.local.PreferencesHelper;
import floor.twelve.apps.com.medical.data.model.MedicalApi;
import floor.twelve.apps.com.medical.data.remote.RestApi;
import floor.twelve.apps.com.medical.di.scopes.AppScope;
import retrofit2.Retrofit;

/**
 * Created by Alexandra on 05.07.2017.
 */

@Module(includes = { RetrofitModule.class }) public class DataModule {

  @Provides @AppScope MedicalApi provideSalonApi(Retrofit retrofit) {
    return retrofit.create(MedicalApi.class);
  }

  @Provides @AppScope RestApi provideRestApi(MedicalApi api) {
    return new RestApi(api);
  }

  @Provides @AppScope DbHelper provideDbHelper() {
    return new DbHelper();
  }

  @Provides @AppScope DataManager provideDataManager(RestApi restApi,
      PreferencesHelper preferencesHelper, AuthorizationManager authorizationManager,
      DbHelper dbHelper) {
    return new DataManager(restApi, preferencesHelper, authorizationManager, dbHelper);
  }

  @Provides @AppScope PreferencesHelper providePreferencesHelper(Context context) {
    return new PreferencesHelper(context);
  }
}
