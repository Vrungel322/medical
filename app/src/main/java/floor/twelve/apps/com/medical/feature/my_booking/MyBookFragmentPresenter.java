package floor.twelve.apps.com.medical.feature.my_booking;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 14.08.2017.
 */
@InjectViewState public class MyBookFragmentPresenter extends BasePresenter<IMyBookFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
    //RxBus
    subscribeUpdateBooking();
    mRxBus.post(new RxBusHelper.SetBookingItemInMenu());
  }

  private void fetchBookingEntities() {
    Subscription subscription = mDataManager.fetchBookingEntities()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(lastBookingEntities -> {
          getViewState().showAllBooking(lastBookingEntities);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateBooking() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.UpdateLastBookingListEvent.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(updateLastBookingListEvent -> fetchBookingEntities(), throwable -> {
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void startRefreshing() {
    fetchBookingEntities();
  }
}
