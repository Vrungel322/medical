package floor.twelve.apps.com.medical.feature.settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.data.local.LocaleHelper;
import floor.twelve.apps.com.medical.feature.settings.views.ISettingsFragment;

import static floor.twelve.apps.com.medical.utils.Constants.Language.RU;
import static floor.twelve.apps.com.medical.utils.Constants.Language.RUSSIAN;
import static floor.twelve.apps.com.medical.utils.Constants.Language.UK;
import static floor.twelve.apps.com.medical.utils.Constants.Language.UKRAINIAN;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

@InjectViewState
public class SettingsFragmentPresenter extends BasePresenter<ISettingsFragment> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    if (mAuthorizationManager.isAuthorized()) {
      getViewState().addUserProfileFragment();
    }
  }

  public int getLanguagePosition() {
    String language = mDataManager.getSelectedLanguage();
    switch (language) {
      case RUSSIAN:
        return 0;
      case UKRAINIAN:
        return 1;
      default:
        return 0;
    }
  }

  public void saveLanguage(String mSelectedLanguage) {
    String languageCode;
    switch (mSelectedLanguage) {
      case RUSSIAN:
        languageCode = RU;
        break;
      case UKRAINIAN:
        languageCode = UK;
        break;
      default:
        languageCode = UK;
        break;
    }
    LocaleHelper.setLocale(mContext, languageCode);
    mDataManager.setSelectedLanguage(mSelectedLanguage);
  }

  public void showChangeLanguageDialog() {
    getViewState().showChangeLanguageDialog();
  }

  public void closeChangeLanguageDialog() {
    getViewState().closeChangeLanguageDialog();
  }
}
