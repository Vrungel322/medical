package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IBookingDetailMasterFragmentView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 04.05.2017.
 */

@InjectViewState public class BookingDetailMasterFragmentPresenter
    extends BasePresenter<IBookingDetailMasterFragmentView> {

  @Inject BookingEntity mBookingEntity;

  @Override protected void inject() {
    App.initBookingComponent();
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFirstFragment();
    //RxBus
    subscribeNextStep();
    subscribeBackSteps();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    App.destroyBookingComponent();
  }

  private void subscribeNextStep() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.EventForNextStep.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(eventForNextStep -> {
          switch (eventForNextStep.fragmentTag) {
            case Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT:
              getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT);
              break;
            case Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT:
              if (!mBookingEntity.getMasterId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT);
              } else {
                getViewState().showMessageWarning(R.string.error_empty_master);
              }
              break;
            case Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT:
              if (!mBookingEntity.getServiceId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT);
                getViewState().hideKeyboard();
              } else {
                getViewState().showMessageWarning(R.string.error_empty_service);
              }
              break;
            case Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT:
              if (!mBookingEntity.getDateId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT);
              } else {
                getViewState().showMessageWarning(R.string.error_empty_date);
              }
              break;
          }
        });
    addToUnsubscription(subscription);
  }

  public void setSelectedTab(String fragmentTag) {
    getViewState().setSelectedTab(fragmentTag);
  }

  public void clickTab(String fragmentTag) {
    mRxBus.post(new RxBusHelper.EventForNextStep(fragmentTag));
  }

  private void subscribeBackSteps() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.StateBackBookingMaster.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(stateBackBookingMaster -> getViewState().stateBackBookingMaster(), Timber::e);
    addToUnsubscription(subscription);
  }
}
