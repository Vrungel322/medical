package floor.twelve.apps.com.medical.feature.offers.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.offers.views.IAllSalesFragmentView;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;

/**
 * Created by Vrungel on 29.08.2017.
 */
@InjectViewState public class AllSalesFragmentPresenter
    extends BasePresenter<IAllSalesFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchSales()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(salesEntities -> {
          getViewState().addList(salesEntities);
        });
    addToUnsubscription(subscription);

  }
}
