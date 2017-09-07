package floor.twelve.apps.com.medical;

import android.support.multidex.MultiDexApplication;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.arellomobile.mvp.RegisterMoxyReflectorPackages;
import floor.twelve.apps.com.medical.di.components.AppComponent;
import floor.twelve.apps.com.medical.di.components.BookingComponent;
import floor.twelve.apps.com.medical.di.components.DaggerAppComponent;
import floor.twelve.apps.com.medical.di.modules.AppModule;
import floor.twelve.apps.com.medical.di.modules.BookingModule;
import floor.twelve.apps.com.medical.utils.Constants;
import io.realm.Realm;
import shortbread.Shortbread;
import timber.log.Timber;

/**
 * Created by Alexandra on 05.07.2017.
 */

@RegisterMoxyReflectorPackages({ "com.apps.twelve.floor.authorization" }) public class App
    extends MultiDexApplication {

  private static AppComponent sAppComponent;
  private static BookingComponent sBookingComponent;


  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  public static BookingComponent getBookingComponent() {
    return sBookingComponent;
  }

  public static void initBookingComponent() {
    sBookingComponent = sAppComponent.plusBookingComponent(new BookingModule());
  }

  public static void destroyBookingComponent() {
    sBookingComponent = null;
  }

  @Override public void onCreate() {
    super.onCreate();
    //Fabric.with(this, new Crashlytics());
    Shortbread.create(this);
    AuthorizationManager.init(this, Constants.Remote.BASE_URL);
    Realm.init(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }
}
