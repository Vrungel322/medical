package floor.twelve.apps.com.medical.feature.main_screen.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubOffersAdapterView;

/**
 * Created by Vrungel on 11.08.2017.
 */
@InjectViewState public class SubOffersAdapterPresenter
    extends BasePresenter<ISubOffersAdapterView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
