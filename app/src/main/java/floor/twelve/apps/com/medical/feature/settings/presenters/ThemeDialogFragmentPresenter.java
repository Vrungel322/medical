package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.settings.views.IThemeDialogFragmentView;

/**
 * Created by John on 29.06.2017.
 */
@InjectViewState public class ThemeDialogFragmentPresenter
    extends BasePresenter<IThemeDialogFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().showSetThemeDialog(mDataManager.getThemeSelected());
  }

  public void setThemeApp(int themeApp) {
    mDataManager.setThemeSelected(themeApp);
  }

  public void showThemeApp(int positionTheme) {
    getViewState().showThemeApp(positionTheme);
  }

  public void revertThemeApp() {
    getViewState().showThemeApp(mDataManager.getThemeSelected());
  }
}
