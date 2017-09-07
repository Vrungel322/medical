package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IChooseMasterServiceFragmentView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;

@InjectViewState public class ChooseMasterServiceFragmentPresenter
    extends BasePresenter<IChooseMasterServiceFragmentView> {

  @Inject BookingEntity mBookingEntity;

  private List<ServiceEntity> mServiceEntities = new ArrayList<>();

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpRvServices();
    fetchAllServicesByMasterId();
  }

  @SuppressWarnings("ConstantConditions") private void fetchAllServicesByMasterId() {
    getViewState().setMasterName(mBookingEntity.getMasterName());
    Subscription subscription =
        mDataManager.fetchAllServicesByMasterId(mBookingEntity.getMasterId())
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(response -> {
              if (response.code() == RESPONSE_200) {
                getViewState().hideProgressBar();
                getViewState().updateRvServices(response.body(), mBookingEntity.getServiceId());
                mServiceEntities.clear();
                mServiceEntities.addAll(response.body());
              }
            }, throwable -> {
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void filterServices(String s) {
    Subscription subscription;
    if (!s.isEmpty()) {
      subscription = Observable.from(mServiceEntities)
          .filter(serviceEntity -> serviceEntity.getTitle().toLowerCase().contains(s.trim()))
          .toList()
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities,
              mBookingEntity.getServiceId()), Timber::e);
    } else {
      subscription = Observable.just(mServiceEntities)
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities,
              mBookingEntity.getServiceId()), Timber::e);
    }
    addToUnsubscription(subscription);
  }

  public void setItemSelected(int position) {
    mBookingEntity.setServiceId(String.valueOf(mServiceEntities.get(position).getServiceId()));
    mBookingEntity.setServiceName(mServiceEntities.get(position).getTitle());
    mBookingEntity.setDurationServices(mServiceEntities.get(position).getTime());
    getViewState().setItemSelected(position);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT));
  }

  public void clearLastBookingEntity() {
    mBookingEntity.setDateId("");
  }
}
