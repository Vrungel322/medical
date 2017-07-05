package floor.twelve.apps.com.medical;

import android.support.multidex.MultiDexApplication;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.evernote.android.job.JobManager;
import floor.twelve.apps.com.medical.di.components.AppComponent;
import floor.twelve.apps.com.medical.di.components.DaggerAppComponent;
import floor.twelve.apps.com.medical.di.modules.AppModule;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.jobs.JobsCreator;
import shortbread.Shortbread;
import timber.log.Timber;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class App extends MultiDexApplication {

  private static AppComponent sAppComponent;

  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();
    //Fabric.with(this, new Crashlytics());
    Shortbread.create(this);
    AuthorizationManager.init(this, Constants.Remote.BASE_URL);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    //after initialize modules dagger
    JobManager.create(this).addJobCreator(new JobsCreator());
  }
}
