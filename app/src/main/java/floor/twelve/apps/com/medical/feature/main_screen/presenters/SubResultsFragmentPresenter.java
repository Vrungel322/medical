package floor.twelve.apps.com.medical.feature.main_screen.presenters;

import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubResultsFragment;
import rx.Subscription;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

@InjectViewState public class SubResultsFragmentPresenter
    extends BasePresenter<ISubResultsFragment> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchLastResultEntities();
  }

  private void fetchLastResultEntities() {
    Subscription subscription = mDataManager.fetchLastResultEntities()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(resultEntities -> getViewState().showLastResults(resultEntities));
    addToUnsubscription(subscription);
  }
}
