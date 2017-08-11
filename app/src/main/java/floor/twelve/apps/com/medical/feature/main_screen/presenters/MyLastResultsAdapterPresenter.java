package floor.twelve.apps.com.medical.feature.main_screen.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.IMyLastResultsAdapter;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

@InjectViewState public class MyLastResultsAdapterPresenter
    extends BasePresenter<IMyLastResultsAdapter> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
