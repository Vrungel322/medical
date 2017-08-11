package floor.twelve.apps.com.medical.feature.results.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.results.views.IResultsAdapter;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

@InjectViewState public class ResultsAdapterPresenter extends BasePresenter<IResultsAdapter> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
