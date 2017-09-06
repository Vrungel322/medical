package floor.twelve.apps.com.medical.feature.doctors.presenters;

import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsServicesAdapterView;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class DoctorsServicesAdapterPresenter extends BasePresenter<IDoctorsServicesAdapterView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
