package floor.twelve.apps.com.medical.feature.about_us.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.about_us.views.IAboutUsFragmentView;

/**
 * Created by Vrungel on 31.08.2017.
 */
@InjectViewState public class AboutUsFragmentPresenter extends BasePresenter<IAboutUsFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
