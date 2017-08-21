package floor.twelve.apps.com.medical.feature.news.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.news.views.INewsDetailFragmentView;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class DetailNewsFragmentPresenter
    extends BasePresenter<INewsDetailFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
