package floor.twelve.apps.com.medical.feature.doctors.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsReviewsAdapterView;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

@InjectViewState public class DoctorsReviewsAdapterPresenter
    extends BasePresenter<IDoctorsReviewsAdapterView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
