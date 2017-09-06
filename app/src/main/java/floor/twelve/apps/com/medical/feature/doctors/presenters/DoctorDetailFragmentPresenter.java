package floor.twelve.apps.com.medical.feature.doctors.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorDetailFragmentView;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

@InjectViewState public class DoctorDetailFragmentPresenter
    extends BasePresenter<IDoctorDetailFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
