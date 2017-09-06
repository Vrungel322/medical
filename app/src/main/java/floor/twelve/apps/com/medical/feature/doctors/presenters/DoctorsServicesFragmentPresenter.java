package floor.twelve.apps.com.medical.feature.doctors.presenters;

import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsServicesFragmentView;
import rx.Subscription;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

@InjectViewState public class DoctorsServicesFragmentPresenter
    extends BasePresenter<IDoctorsServicesFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchDoctorsServices()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(serviceEntities -> getViewState().showServices(serviceEntities));
    addToUnsubscription(subscription);
  }
}
