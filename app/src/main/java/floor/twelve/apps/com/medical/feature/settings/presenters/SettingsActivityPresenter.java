package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.settings.views.ISettingsActivity;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

@InjectViewState public class SettingsActivityPresenter extends BasePresenter<ISettingsActivity> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addSettingsFragment();
  }
}
