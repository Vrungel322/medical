package floor.twelve.apps.com.medical.feature.doctors.presenters;

import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsAdapterView;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

public class DoctorsAdapterPresenter extends BasePresenter<IDoctorsAdapterView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
