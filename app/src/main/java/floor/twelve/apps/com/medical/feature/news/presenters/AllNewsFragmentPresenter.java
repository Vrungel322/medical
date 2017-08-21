package floor.twelve.apps.com.medical.feature.news.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.feature.news.views.IAllNewsFragmentView;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;
import timber.log.Timber;

import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;
import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_503;

/**
 * Created by Vrungel on 24.02.2017.
 */

@InjectViewState public class AllNewsFragmentPresenter extends BasePresenter<IAllNewsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfNews();
    mRxBus.post(new RxBusHelper.SetNewsItemInMenu());
  }

  public void fetchListOfNews() {
    getViewState().addListOfNews(mDataManager.getAllElementsFromDB(NewsEntity.class));
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchAllNews()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            getViewState().addListOfNews(response.body());
            getViewState().stopRefreshingView();
          }
          if (response.code() == RESPONSE_503) {
            getViewState().showServerErrorMsg();
            getViewState().stopRefreshingView();
          }
        }, throwable -> {
          Timber.e(throwable);
          getViewState().stopRefreshingView();
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }
}
