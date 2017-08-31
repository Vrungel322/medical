package floor.twelve.apps.com.medical.feature.prices.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesFragmentView;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

@InjectViewState public class PricesFragmentPresenter extends BasePresenter<IPricesFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  //@Override protected void onFirstViewAttach() {
  //  super.onFirstViewAttach();
  //  Subscription subscription = mDataManager.fetchPriceByCategory()
  //      .compose(ThreadSchedulers.applySchedulers())
  //      .subscribe(priceEntities -> getViewState().addListPricesEntities(priceEntities));
  //  addToUnsubscription(subscription);
  //}
}
