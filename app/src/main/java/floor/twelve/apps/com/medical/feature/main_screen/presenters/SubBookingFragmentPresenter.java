package floor.twelve.apps.com.medical.feature.main_screen.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubBookingFragmentView;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;
import timber.log.Timber;

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
    subscribeUpdateSubBooking();
  }

  private void fetchBookingEntities() {
    Subscription subscription = mDataManager.fetchBookingEntities()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(lastBookingEntities -> {
          getViewState().showLastBookings(lastBookingEntities);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateSubBooking() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.UpdateLastBookingListEvent.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(updateLastBookingListEvent -> fetchBookingEntities(), throwable -> {
              subscribeUpdateSubBooking();
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }
}
