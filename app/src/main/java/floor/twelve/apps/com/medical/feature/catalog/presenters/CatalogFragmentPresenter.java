package floor.twelve.apps.com.medical.feature.catalog.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import floor.twelve.apps.com.medical.feature.catalog.views.ICatalogFragmentView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.Converters;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import java.util.ArrayList;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by John on 17.05.2017.
 */

@InjectViewState public class CatalogFragmentPresenter extends BasePresenter<ICatalogFragmentView> {

  private String mTitle;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchGoodsList();
    //RxBus
    subscribeGoodsList();
    subscribeReloadListByCategory();
    subscribeReloadListByCategoryLocally();
    subscribeShowResetBtn();
  }

  private void subscribeShowResetBtn() {

    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ShowResetBtn.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(showResetBtn -> {
          getViewState().showResetBtn();
        });
    addToUnsubscription(subscription);
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }

  private void subscribeReloadListByCategoryLocally() {
    final Integer[] id = new Integer[1];
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.ReloadCatalogByCategoryLocally.class)
            .compose(ThreadSchedulers.applySchedulers())
            .concatMap(reloadCatalogByCategoryLocally -> {
              id[0] = reloadCatalogByCategoryLocally.id;
              //return Observable.just(mDataManager.getAllElementsFromDB(GoodsEntity.class));
              return Observable.just(
                  new ArrayList<>(mDataManager.getAllElementsFromDB(GoodsEntity.class)));
            })
            .concatMap(goodsEntities -> {
              ArrayList<GoodsEntity> entities = new ArrayList<GoodsEntity>();
              for (int i = 0; i < goodsEntities.size(); i++) {
                if (String.valueOf(goodsEntities.get(i).getCategoryId())
                    .equals(String.valueOf(id[0]))) {
                  entities.add(goodsEntities.get(i));
                }
              }
              return Observable.just(entities);
            })

            .subscribe(goodsEntities -> {
              goodsEntities.add(0,
                  new GoodsEntity(0, mContext.getString(R.string.menu_favourite), "", "", "", "", 0,
                      "", "", Converters.getUrl(R.drawable.ic_favorite_catalog_32dp), 0, null,
                      false, false, false));
              getViewState().updateGoodsList(goodsEntities);
            });
    addToUnsubscription(subscription);
  }

  private void subscribeReloadListByCategory() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ReloadCatalogByCategory.class)
        .doOnNext(reloadCatalogByCategory -> getViewState().startRefreshingView())
        .concatMap(reloadCatalogByCategory -> {
          mTitle = reloadCatalogByCategory.title;
          return mDataManager.fetchGoodsByCatalogId(reloadCatalogByCategory.id);
        })
        .concatMap(response -> {
          if (response.code() == RESPONSE_200) {
            return Observable.just(response.body());
          } else {
            return Observable.error(new Exception("Not response 200"));
          }
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(goodsEntities -> {
          goodsEntities.add(0,
              new GoodsEntity(0, mContext.getString(R.string.menu_favourite), "", "", "", "", 0, "",
                  "", Converters.getUrl(R.drawable.ic_favorite_catalog_32dp), 0, null, false, false,
                  false));
          getViewState().updateGoodsList(goodsEntities);
          getViewState().stopRefreshingView();
          getViewState().setCategoryTitle(mTitle);
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  @SuppressWarnings("ConstantConditions") public void fetchGoodsList() {

    getViewState().updateGoodsList(mDataManager.getAllElementsFromDB(GoodsEntity.class));
    getViewState().startRefreshingView();
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.fetchAllProducts()).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.fetchAllProducts());
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              response.body()
                  .add(0,
                      new GoodsEntity(0, mContext.getString(R.string.menu_favourite), "", "", "",
                          "", 0, "", "", Converters.getUrl(R.drawable.ic_favorite_catalog_32dp), 0,
                          null, false, false, false));
              cacheEntities(response.body(), GoodsEntity.class);

              getViewState().updateGoodsList(response.body());
              getViewState().stopRefreshingView();
              getViewState().setButtonDefaultText();
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              getViewState().stopRefreshingView();
              getViewState().setButtonDefaultText();
              break;
            case Constants.StatusCode.RESPONSE_503:
              getViewState().showServerErrorMsg();
              getViewState().stopRefreshingView();
            default:
              getViewState().stopRefreshingView();
              getViewState().setButtonDefaultText();
              showMessageException();
              break;
          }
        }, throwable -> {
          getViewState().stopRefreshingView();
          getViewState().setButtonDefaultText();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeGoodsList() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateGoodsList.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(goodsEntities -> fetchGoodsList(), Timber::e);
    addToUnsubscription(subscription);
  }
}
