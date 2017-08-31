package floor.twelve.apps.com.medical.feature.prices.presenters;

import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesCategoriesFragmentView;
import rx.Subscription;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

@InjectViewState public class PricesCategoriesFragmentPresenter
    extends BasePresenter<IPricesCategoriesFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchListOfPricesCategories()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(
            pricesCategoryEntities -> getViewState().showPricesCategories(pricesCategoryEntities));
    addToUnsubscription(subscription);
  }
}
