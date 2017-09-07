package floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.data.model.CategoryEntity;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.views.IChooseServiceServiceFragmentView;
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
import static floor.twelve.apps.com.medical.utils.Constants.StatusCode.RESPONSE_503;

/**
 * Created by Vrungel on 29.03.2017.
 */

@InjectViewState public class ChooseServiceServiceFragmentPresenter
    extends BasePresenter<IChooseServiceServiceFragmentView> {

  private static final String SLASH = "/";
  @Inject BookingEntity mBookingEntity;
  private List<ServiceEntity> mServiceAllEntities = new ArrayList<>();
  private List<List<CategoryEntity>> mListListCategories = new ArrayList<>();
  private List<String> mPathList = new ArrayList<>();
  private StringBuilder mPath = new StringBuilder();

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpRvAllServices();
    getViewState().setUpRvCategory();
    fetchCategory();
    //RxBus
    subscribeBackCategories();
  }

  private void fetchCategory() {
    getViewState().showProgressBar();
    Subscription subscription = mDataManager.fetchCategory()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            getViewState().hideProgressBar();
            getViewState().updateRvCategory(response.body());
            mListListCategories.add(response.body());
          }
          if (response.code() == RESPONSE_503) {
            getViewState().showServerErrorMsg();
          }
        }, throwable -> {
          Timber.e(throwable);
          getViewState().hideProgressBar();
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  @SuppressWarnings("ConstantConditions") public void getServiceWithParentId(int parentId) {
    getViewState().stateCategoriesServices(false);
    Subscription subscription = mDataManager.fetchServicesOfCategoryWithId(parentId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            getViewState().setServicesWithParentId(response.body());
            mServiceAllEntities.clear();
            mServiceAllEntities.addAll(response.body());
            mListListCategories.add(new ArrayList<>());
            getViewState().showTextPath(String.valueOf(mPath));
            getViewState().stateCategoriesServices(true);
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void getCategoriesWithParentId(int parentId) {
    getViewState().stateCategoriesServices(false);
    Subscription subscription = mDataManager.fetchCategoriesOfCategoryWithId(parentId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            getViewState().setCategoriesWithParentId(response.body());
            mListListCategories.add(response.body());
            getViewState().showTextPath(String.valueOf(mPath));
            getViewState().stateCategoriesServices(true);
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  @SuppressWarnings("ConstantConditions") public void fetchAllServices() {
    getViewState().showProgressBarAllServices();
    Subscription subscription = mDataManager.fetchAllServices()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            getViewState().updateRvAllServices(response.body());
            mServiceAllEntities.clear();
            mServiceAllEntities.addAll(response.body());
            getViewState().hideProgressBarAllServices();
          }
        }, throwable -> {
          Timber.e(throwable);
          getViewState().hideProgressBarAllServices();
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void filterServices(String s) {
    Subscription subscription;
    if (!s.isEmpty()) {
      subscription = Observable.from(mServiceAllEntities)
          .filter(serviceEntity -> serviceEntity.getTitle().toLowerCase().contains(s.trim()))
          .toList()
          .subscribe(serviceEntities -> getViewState().updateRvAllServices(serviceEntities),
              Timber::e);

      addToUnsubscription(subscription);
    } else {
      getViewState().updateRvAllServices(mServiceAllEntities);
    }
  }

  public void hideLLAllServices() {
    getViewState().hideLLAllServices();
  }

  public void setItemSelected(int position) {
    mBookingEntity.setServiceId(String.valueOf(mServiceAllEntities.get(position).getServiceId()));
    mBookingEntity.setServiceName(String.valueOf(mServiceAllEntities.get(position).getTitle()));
    mBookingEntity.setDurationServices(mServiceAllEntities.get(position).getTime());
    getViewState().setItemSelected(position);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT));
  }

  public void showLLTreeServices() {
    getViewState().showLLTreeServices();
  }

  public void showLLAllServices() {
    getViewState().showLLAllServices();
  }

  public void hideLLTreeServices() {
    getViewState().hideLLTreeServices();
  }

  public void showTextPath(String text) {
    mPath.append(text).append(SLASH);
    mPathList.add(text);
  }

  private void subscribeBackCategories() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.BackCategories.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(backCategories -> {
          if (mListListCategories.size() > 1) {
            getViewState().setCategoriesWithParentId(
                mListListCategories.get(mListListCategories.size() - 2));
            mListListCategories.remove(mListListCategories.size() - 1);

            mPath.setLength(0);
            mPathList.remove(mPathList.size() - 1);
            for (String s : mPathList) {
              mPath.append(s).append(SLASH);
            }
            getViewState().showTextPath(String.valueOf(mPath));

            if (mListListCategories.size() == 1) {
              getViewState().hideTextPath();
            }
          } else {
            mRxBus.post(new RxBusHelper.CloseBookingService());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void clearLastBookingEntity() {
    mBookingEntity.setMasterId("");
    mBookingEntity.setDateId("");
  }
}
