package floor.twelve.apps.com.medical.feature.main_screen.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.IMainFragmentView;
import floor.twelve.apps.com.medical.utils.RxBusHelper;

/**
 * Created by Vrungel on 09.08.2017.
 */

@InjectViewState public class MainFragmentPresenter extends BasePresenter<IMainFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addSubOffersAndLastResults();
    //getViewState().startRefreshingView();
    //RxBus
  }

  public void updateOffersAndLastResults() {
    getViewState().startRefreshingView();
    mRxBus.post(new RxBusHelper.UpdateOffers());
    if (mAuthorizationManager.isAuthorized()) {
      mRxBus.post(new RxBusHelper.UpdateLastResultsListEvent());
    }
  }
}
