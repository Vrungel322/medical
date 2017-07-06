package floor.twelve.apps.com.medical.feature.start_point.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.start_point.views.IStartActivityView;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 05.07.2017.
 */

@InjectViewState public class StartActivityPresenter extends BasePresenter<IStartActivityView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentMain();
    //RxBus
    subscribeOnEvents();
    subscribeConnectException();
    subscribeWrongException();
    subscribeLogoutUser();
    subscribeShowDialog();
    subscribeUnauthorizedUser();
  }

  public void share() {
    getViewState().share();
  }

  private void subscribeOnEvents() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.HideFloatingButton.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe((event -> getViewState().hideFloatingButton()), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.ShowFloatingButton.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe((event -> getViewState().showFloatingButton()), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeShowDialog() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ShowAuthDialog.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(show -> showAlertDialog(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeLogoutUser() {
    Subscription subscription = mAuthorizationManager.getAuthRxBus()
        .filteredObservable(AuthRxBusHelper.LogoutEvent.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> {
          getViewState().logoutUser();
          //mDataManager.logoutUser();
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeUnauthorizedUser() {
    Subscription subscription = mAuthorizationManager.getAuthRxBus()
        .filteredObservable(AuthRxBusHelper.UnauthorizedEvent.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().startSignInActivity(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeWrongException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageWrongException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showWrongMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  public void setDrawerIndicator() {
    getViewState().setDrawerIndicator();
  }

  public void showAlertDialog() {
    getViewState().showAlertDialog();
  }

  public void cancelAlertDialog() {
    getViewState().cancelAlertDialog();
  }
}
