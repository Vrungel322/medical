package floor.twelve.apps.com.medical.di.modules;

import android.app.Application;
import android.content.Context;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import dagger.Module;
import dagger.Provides;
import floor.twelve.apps.com.medical.base.Navigator;
import floor.twelve.apps.com.medical.di.scopes.AppScope;
import floor.twelve.apps.com.medical.utils.RxBus;
import floor.twelve.apps.com.medical.utils.jobs.JobsCreator;

/**
 * Created by Alexandra on 05.07.2017.
 */

@Module(includes = { DataModule.class }) public class AppModule {

  private final Application mApplication;

  public AppModule(Application application) {
    mApplication = application;
  }

  @Provides @AppScope Context provideAppContext() {
    return mApplication;
  }

  @Provides @AppScope RxBus provideRxBus() {
    return new RxBus();
  }

  @Provides @AppScope JobsCreator provideJobsCreator() {
    return new JobsCreator();
  }

  @Provides @AppScope Navigator provideNavigator() {
    return new Navigator();
  }

  @Provides @AppScope AuthorizationManager provideAuthorizationManager() {
    return AuthorizationManager.getInstance();
  }
}