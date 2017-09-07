package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.data.model.MasterEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IChooseMasterMasterFragmentView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.Randomizer;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_200;
import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_503;

@InjectViewState public class ChooseMasterMasterFragmentPresenter
    extends BasePresenter<IChooseMasterMasterFragmentView> {

  @Inject BookingEntity mBookingEntity;
  private List<MasterEntity> mMasterEntities;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
    fetchAllMasters();
  }

  private void fetchAllMasters() {
    Subscription subscription = mDataManager.fetchAllMasters()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            mMasterEntities = response.body();
            getViewState().showMasters(response.body());
            getViewState().hideProgressBar();
          }
          if (response.code() == RESPONSE_503) {
            getViewState().showServerErrorMsg();
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void setSelectedItem(int position) {
    mBookingEntity.setMasterId(mMasterEntities.get(position).getMasterId());
    mBookingEntity.setMasterName(mMasterEntities.get(position).getMasterName());
    getViewState().setSelectedItem(position + 1);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT));
  }

  public void setAnyMasterSelected() {
    mBookingEntity.setMasterId(
        mMasterEntities.get(Randomizer.getRandomNumberInRange(1, mMasterEntities.size()))
            .getMasterId());
    mBookingEntity.setMasterName(mContext.getString(R.string.any_master));
    getViewState().setSelectedItem(0);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT));
  }

  public void clearLastBookingEntity() {
    mBookingEntity.setServiceId("");
    mBookingEntity.setDateId("");
  }
}
