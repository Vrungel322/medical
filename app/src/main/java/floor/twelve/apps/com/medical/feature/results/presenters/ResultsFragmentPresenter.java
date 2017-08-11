package floor.twelve.apps.com.medical.feature.results.presenters;

import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.results.views.IResultsFragment;
import rx.Subscription;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

@InjectViewState public class ResultsFragmentPresenter extends BasePresenter<IResultsFragment> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchResultEntities();
  }

  private void fetchResultEntities() {
    Subscription subscription = mDataManager.fetchResultEntities()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(resultEntities -> getViewState().showResults(resultEntities));
    addToUnsubscription(subscription);
  }
}
