package floor.twelve.apps.com.medical.feature.catalog.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.catalog.views.IStaffDetailsFragmentView;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;
import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_204;

/**
 * Created by Vrungel on 18.05.2017.
 */
@InjectViewState public class GoodsDetailsFragmentPresenter
    extends BasePresenter<IStaffDetailsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void addFavorite(int idGoods) {
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.addToFavoriteGoods(idGoods))
            .concatMap(response -> {
              if (response.code() == RESPONSE_TOKEN_EXPIRED) {
                return mAuthorizationManager.checkToken(mDataManager.addToFavoriteGoods(idGoods));
              }
              return Observable.just(response);
            })
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(response -> {
              switch (response.code()) {
                case RESPONSE_200:
                  getViewState().setStatusFavorite(true);
                  mRxBus.post(new RxBusHelper.UpdateGoodsList());
                  mRxBus.post(new RxBusHelper.UpdateFavoriteGoodsList());
                  break;
                case RESPONSE_UNAUTHORIZED:
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
                  break;
                default:
                  showMessageException();
                  break;
              }
            }, throwable -> {
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void deleteFavorite(int idGoods) {
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.removeFromFavoriteGoods(idGoods))
            .concatMap(response -> {
              if (response.code() == RESPONSE_TOKEN_EXPIRED) {
                return mAuthorizationManager.checkToken(
                    mDataManager.removeFromFavoriteGoods(idGoods));
              }
              return Observable.just(response);
            })
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(response -> {
              switch (response.code()) {
                case RESPONSE_204:
                  getViewState().setStatusFavorite(false);
                  mRxBus.post(new RxBusHelper.UpdateGoodsList());
                  mRxBus.post(new RxBusHelper.UpdateFavoriteGoodsList());
                  break;
                case RESPONSE_UNAUTHORIZED:
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
                  break;
                default:
                  showMessageException();
                  break;
              }
            }, throwable -> {
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }
}
