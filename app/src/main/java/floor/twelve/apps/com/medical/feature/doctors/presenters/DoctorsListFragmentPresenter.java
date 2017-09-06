package floor.twelve.apps.com.medical.feature.doctors.presenters;

import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsListFragmentView;
import rx.Subscription;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

@InjectViewState public class DoctorsListFragmentPresenter
    extends BasePresenter<IDoctorsListFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchAllDoctors()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(list -> getViewState().showDoctors(list));
    addToUnsubscription(subscription);
  }
}
