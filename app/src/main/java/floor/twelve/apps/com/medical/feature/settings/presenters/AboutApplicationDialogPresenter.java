package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.settings.views.IAboutApplicationDialog;

/**
 * Created by alexandersvyatetsky on 4/08/17.
 */

@InjectViewState public class AboutApplicationDialogPresenter
    extends BasePresenter<IAboutApplicationDialog> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
