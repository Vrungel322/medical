package floor.twelve.apps.com.medical.feature.catalog.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.goods.category.Genre;
import floor.twelve.apps.com.medical.data.model.goods.category.GoodsCategoryEntity;
import floor.twelve.apps.com.medical.feature.catalog.views.ICategoryDialogFragmentView;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import java.util.ArrayList;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by Vrungel on 29.05.2017.
 */
@InjectViewState public class CategoryDialogFragmentPresenter
    extends BasePresenter<ICategoryDialogFragmentView> {

  private ArrayList<Genre> mGenres = new ArrayList<Genre>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchCategories();
  }

  @SuppressWarnings("ConstantConditions") private void fetchCategories() {
    ArrayList<GoodsCategoryEntity> goodsCategoryEntities = new ArrayList<>();
    goodsCategoryEntities.addAll(mDataManager.getAllElementsFromDB(GoodsCategoryEntity.class));
    for (int i = 0; i < goodsCategoryEntities.size(); i++) {
      mGenres.add(new Genre(goodsCategoryEntities.get(i).getTitle(),
          goodsCategoryEntities.get(i).getChildren()));
    }
    getViewState().fillCategories(mGenres);
    mGenres.clear();
    getViewState().startProgressBar();
    Subscription subscription = mDataManager.fetchCategories().concatMap(response -> {
      if (response.code() == RESPONSE_200) {
        return Observable.from(response.body());
      } else {
        return Observable.error(new Exception("Not response 200"));
      }
    }).toList().doOnNext(goodsCategoryEntities1 -> {
      cacheEntities(goodsCategoryEntities1, GoodsCategoryEntity.class);
    }).concatMap(Observable::from).concatMap(goodsCategoryEntity -> {
      mGenres.add(new Genre(goodsCategoryEntity.getTitle(), goodsCategoryEntity.getChildren()));
      return Observable.just(mGenres);
    }).compose(ThreadSchedulers.applySchedulers()).subscribe(genres -> {
      getViewState().stopProgressBar();
      getViewState().fillCategories(genres);
    }, throwable -> {
      showMessageException(throwable);
      getViewState().stopProgressBar();
      Timber.e(throwable);
    });
    addToUnsubscription(subscription);
  }

  public void postEventToReloadList(Integer id, String title) {
    mRxBus.post(new RxBusHelper.ReloadCatalogByCategory(id, title));
  }

  public void postToShowResetBtn() {
    mRxBus.post(new RxBusHelper.ShowResetBtn());
  }

  public void postEventToReloadListLocally(Integer id, String title) {
    mRxBus.post(new RxBusHelper.ReloadCatalogByCategoryLocally(id, title));
  }
}
