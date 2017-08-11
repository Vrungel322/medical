package floor.twelve.apps.com.medical.feature.main_screen.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubOffersFragmentView;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;

/**
 * Created by Vrungel on 11.08.2017.
 */
@InjectViewState public class SubOffersFragmentPresenter
    extends BasePresenter<ISubOffersFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchOfferEntities();
  }
  private void fetchOfferEntities() {
    Subscription subscription = mDataManager.fetchOfferEntities()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(lastBookingEntities -> {
          getViewState().showOffers(lastBookingEntities);
        });
    addToUnsubscription(subscription);
  }
}
