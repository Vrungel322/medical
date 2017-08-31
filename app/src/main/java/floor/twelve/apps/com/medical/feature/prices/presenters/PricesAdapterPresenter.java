package floor.twelve.apps.com.medical.feature.prices.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesAdapterView;

/**
 * Created by alexandersvyatetsky on 31/08/17.
 */

@InjectViewState public class PricesAdapterPresenter extends BasePresenter<IPricesAdapterView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
