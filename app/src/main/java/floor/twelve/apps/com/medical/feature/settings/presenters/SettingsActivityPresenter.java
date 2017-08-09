package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.settings.views.ISettingsActivity;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;
import timber.log.Timber;

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
    subscribeLogoutUser();
  }

  private void subscribeLogoutUser() {
    Subscription subscription = mAuthorizationManager.getAuthRxBus()
        .filteredObservable(AuthRxBusHelper.LogoutEvent.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> {
          getViewState().logoutUser();
          mDataManager.logoutUser();
        }, Timber::e);
    addToUnsubscription(subscription);
  }
}
