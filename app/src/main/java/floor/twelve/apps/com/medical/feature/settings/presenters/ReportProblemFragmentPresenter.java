package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.DataManager;
import floor.twelve.apps.com.medical.feature.settings.views.IReportProblemFragmentView;
import javax.inject.Inject;

/**
 * Created by Alexandra on 05.05.2017.
 */

@InjectViewState public class ReportProblemFragmentPresenter
    extends BasePresenter<IReportProblemFragmentView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void sendProblem(String problem) {
    //Subscription subscription = mDataManager.sendReportProblem(problem)
    //    .compose(ThreadSchedulers.applySchedulers())
    //    .doOnNext(reportProblemResponseEntityResponse -> {
    //      if (reportProblemResponseEntityResponse.code() == 200) {
    //        getViewState().stopAnimation();
    //        getViewState().openSnackBarOK();
    //      } else {
    //        getViewState().showAlert();
    //      }
    //    })
    //    .delay(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
    //    .subscribe(reportProblemResponseEntityResponse -> {
    //      if (reportProblemResponseEntityResponse.code() == 200) {
    //        getViewState().closeFragment();
    //      } else {
    //        getViewState().revertAnimation();
    //      }
    //    }, throwable -> {
    //      Timber.e(throwable);
    //      showMessageException(throwable);
    //    });
    //addToUnsubscription(subscription);
  }
}