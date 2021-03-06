package floor.twelve.apps.com.medical.feature.catalog.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.catalog.views.ICataloFavoriteFragmentView;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by John on 06.06.2017.
 */

@InjectViewState public class CatalogFavoriteFragmentPresenter
    extends BasePresenter<ICataloFavoriteFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchFavoriteGoodsList();
    //RxBus
    subscribeFavoriteGoodsList();
  }

  public void fetchFavoriteGoodsList() {
    getViewState().startRefreshingView();
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.fetchFavoriteGoods()).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.fetchFavoriteGoods());
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              getViewState().stopRefreshingView();
              getViewState().updateGoodsFavoriteList(response.body());
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              getViewState().stopRefreshingView();
              break;
            default:
              getViewState().stopRefreshingView();
              showMessageException();
              break;
          }
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeFavoriteGoodsList() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateFavoriteGoodsList.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(updateFavoriteGoodsList -> fetchFavoriteGoodsList(), Timber::e);
    addToUnsubscription(subscription);
  }
}
