package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.data.model.DataServiceEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IChooseMasterTimeFragmentView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;

@InjectViewState public class ChooseMasterTimeFragmentPresenter
    extends BasePresenter<IChooseMasterTimeFragmentView> {

  @Inject BookingEntity mBookingEntity;
  private List<DataServiceEntity> mDataServiceEntity;
  private int dayPosition;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getTimeMaster();
  }

  @SuppressWarnings("ConstantConditions") private void getTimeMaster() {
    Subscription subscription = mDataManager.fetchDaysDataWithMasterId(mBookingEntity.getMasterId())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            mDataServiceEntity = response.body();
            getViewState().hideProgressBarBookingTime();
            getViewState().setUpRedSquare(mBookingEntity.getServiceName(),
                mBookingEntity.getMasterName());
            if (!response.body().isEmpty()) {
              getViewState().setUpUi(response.body());
              setDateToTv();
              getViewState().showTimeBooking();
            } else {
              getViewState().showNotTime();
            }
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void setSelectedTime(int position) {
    if (mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getStatus()) {
      mBookingEntity.setDateId(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getId()));
      mBookingEntity.setServiceTime(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getTime()));
      getViewState().setSelectedTime(position);
      mBookingEntity.setRemainTimeInSec(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getTimeInSec());

      mRxBus.post(
          new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT));
    } else {
      getViewState().timeIsNotAvailable();
    }
  }

  public void setDateToTv() {
    getViewState().setTextToDayTv();
  }

  public void setSelectedDay(int position) {
    dayPosition = position;
    getViewState().setSelectedDay(position);
  }

  public void clearSelectedTime() {
    mBookingEntity.setDateId("");
    getViewState().clearSelectedTime();
  }
}
