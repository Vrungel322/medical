package floor.twelve.apps.com.medical.feature.settings.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseActivity;
import floor.twelve.apps.com.medical.data.local.LocaleHelper;
import floor.twelve.apps.com.medical.feature.settings.fragments.SettingsFragment;
import floor.twelve.apps.com.medical.feature.settings.presenters.SettingsActivityPresenter;
import floor.twelve.apps.com.medical.feature.settings.views.ISettingsActivity;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import floor.twelve.apps.com.medical.utils.ThemeUtils;

import static floor.twelve.apps.com.medical.utils.Constants.Language.RU;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

public class SettingsActivity extends BaseActivity implements ISettingsActivity {
  @InjectPresenter SettingsActivityPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeActionBar(getBaseContext()));
    setContentView(R.layout.activity_settings);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.menu_settings);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void addSettingsFragment() {
    mNavigator.addFragment(this, R.id.container_settings, SettingsFragment.getInstance());
  }

  @Override public void logoutUser() {
    mNavigator.startActivityClearStack(this, new Intent(this, StartActivity.class));
  }

  @Override public void startSignInActivity() {
    mAuthorizationManager.startSignInActivity(this, ThemeUtils.getThemeActionBar(this),
        LocaleHelper.getLanguage(SettingsActivity.this));
  }

  @Override public void showWrongMessage() {
    showAlertMessage(getResources().getString(R.string.dialog_error_title),
        getResources().getString(R.string.wrong));
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(LocaleHelper.onAttach(base, RU));
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    LocaleHelper.onAttach(getBaseContext());
    //update locale and resources, configuration on each config change
  }
}
