package floor.twelve.apps.com.medical.feature.start_point.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.start_point.views.IStartActivityView;

/**
 * Created by Alexandra on 05.07.2017.
 */

@InjectViewState public class StartActivityPresenter extends BasePresenter<IStartActivityView> {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

}
