package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.settings.views.ISettingsFragment;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

@InjectViewState
public class SettingsFragmentPresenter extends BasePresenter<ISettingsFragment> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    if (mAuthorizationManager.isAuthorized()) {
      getViewState().addUserProfileFragment();
    }
  }
}
