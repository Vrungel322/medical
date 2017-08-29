package floor.twelve.apps.com.medical.feature.prices.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesCategoriesAdapterPresenter;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

@InjectViewState public class PricesCategoriesAdapterPresenter
    extends BasePresenter<IPricesCategoriesAdapterPresenter> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
