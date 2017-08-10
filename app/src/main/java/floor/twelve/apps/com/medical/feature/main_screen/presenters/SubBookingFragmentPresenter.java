package floor.twelve.apps.com.medical.feature.main_screen.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubBookingFragmentView;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;

/**
 * Created by Vrungel on 10.08.2017.
 */
@InjectViewState public class SubBookingFragmentPresenter
    extends BasePresenter<ISubBookingFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
  }

  private void fetchBookingEntities() {
    Subscription subscription = mDataManager.fetchBookingEntities()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(lastBookingEntities -> {
          getViewState().showLastBookings(lastBookingEntities);
        });
    addToUnsubscription(subscription);
  }
}
