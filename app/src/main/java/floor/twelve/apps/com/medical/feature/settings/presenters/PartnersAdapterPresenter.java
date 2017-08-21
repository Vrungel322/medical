package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.settings.views.IPartnersAdapter;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

@InjectViewState public class PartnersAdapterPresenter extends BasePresenter<IPartnersAdapter> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
