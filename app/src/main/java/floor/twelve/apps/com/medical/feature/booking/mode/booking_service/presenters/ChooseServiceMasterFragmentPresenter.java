package floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.data.model.MasterEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.views.IChooseServiceMasterFragmentView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.Randomizer;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 28.03.2017.
 */

@InjectViewState public class ChooseServiceMasterFragmentPresenter
    extends BasePresenter<IChooseServiceMasterFragmentView> {

  @Inject BookingEntity mBookingEntity;
  private List<MasterEntity> mMasterEntities;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
    fetchDoctors();
  }

  private void fetchDoctors() {
    // TODO: 8/09/17 for tests
    //Subscription subscription =
    //    mDataManager.fetchMasters(mBookingEntity.getServiceId(), mBookingEntity.getDateId())
    //        .compose(ThreadSchedulers.applySchedulers())
    //        .subscribe(response -> {
    //          if (response.code() == RESPONSE_200) {
    //            mMasterEntities = response.body();
    //            getViewState().setUpRedSquare(mBookingEntity.getServiceName(),
    //                mContext.getString(R.string.booking_date_and_time,
    //                    Converters.detailDayFromSeconds(mContext,
    //                        mBookingEntity.getRemainTimeInSec()), mBookingEntity.getServiceTime()),
    //                mBookingEntity.getDurationServices());
    //            getViewState().showMasters(response.body());
    //            getViewState().setSelectedItem(mBookingEntity.getMasterId());
    //            getViewState().hideProgressBar();
    //          }
    //        }, throwable -> {
    //          Timber.e(throwable);
    //          showMessageException(throwable);
    //        });

    // TODO: 7/09/17 test data
    Subscription subscription = mDataManager.fetchMasterForTest()
        .compose(com.apps.twelve.floor.authorization.utils.ThreadSchedulers.applySchedulers())
        .subscribe(list -> {
          getViewState().setUpRedSquare("Наращивание носа", "30 июня 2017", "100 мин");
          getViewState().showMasters(list);
          getViewState().hideProgressBar();
        });
    addToUnsubscription(subscription);
  }

  public void setSelectedItem(int position) {
    mBookingEntity.setMasterId(mMasterEntities.get(position).getMasterId());
    mBookingEntity.setMasterName(mMasterEntities.get(position).getMasterName());
    getViewState().setSelectedItem(position + 1);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT));
  }

  public void setAnyMasterSelected() {
    mBookingEntity.setMasterId(
        mMasterEntities.get(Randomizer.getRandomNumberInRange(1, mMasterEntities.size()))
            .getMasterId());
    mBookingEntity.setMasterName(mContext.getString(R.string.any_master));
    getViewState().setSelectedItem(0);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT));
  }
}
