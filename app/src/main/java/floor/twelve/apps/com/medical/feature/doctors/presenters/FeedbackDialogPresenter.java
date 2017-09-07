package floor.twelve.apps.com.medical.feature.doctors.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IFeedbackDialogView;

/**
 * Created by alexandersvyatetsky on 7/09/17.
 */

@InjectViewState public class FeedbackDialogPresenter extends BasePresenter<IFeedbackDialogView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
