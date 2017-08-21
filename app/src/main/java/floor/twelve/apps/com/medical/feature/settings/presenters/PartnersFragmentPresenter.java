package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.settings.views.IPartnersFragment;
import rx.Subscription;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

@InjectViewState public class PartnersFragmentPresenter extends BasePresenter<IPartnersFragment> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchListOfPartners()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(partnerEntities -> getViewState().showPartners(partnerEntities));
    addToUnsubscription(subscription);
  }
}
