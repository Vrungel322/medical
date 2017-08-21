package floor.twelve.apps.com.medical.feature.news.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.news.views.IListNewsDetailFragmentView;

/**
 * Created by John on 11.07.2017.
 */

@InjectViewState public class ListNewsDetailPresenter
    extends BasePresenter<IListNewsDetailFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
