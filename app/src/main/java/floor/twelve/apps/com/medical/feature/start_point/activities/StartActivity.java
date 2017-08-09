package floor.twelve.apps.com.medical.feature.start_point.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.authorization.data.local.LocaleHelper;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseActivity;
import floor.twelve.apps.com.medical.feature.main_screen.fragments.MainFragment;
import floor.twelve.apps.com.medical.feature.start_point.DrawerActivity;
import floor.twelve.apps.com.medical.feature.start_point.presenters.StartActivityPresenter;
import floor.twelve.apps.com.medical.feature.start_point.views.IStartActivityView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.DialogFactory;
import floor.twelve.apps.com.medical.utils.ThemeUtils;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class StartActivity extends DrawerActivity
    implements  IStartActivityView {

  @InjectPresenter StartActivityPresenter mStartActivityPresenter;
  @BindView(R.id.fab_booking) FloatingActionButton mFabBooking;

  private AlertDialog mAuthorizationDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeNoActionBar(getBaseContext()));
    setContentView(R.layout.activity_start);
    super.onCreate(savedInstanceState);

    //mFabBooking.setOnClickListener(v -> mNavigator.startActivity(StartActivity.this,
    //    new Intent(StartActivity.this, BookingActivity.class)));

    getSupportFragmentManager().addOnBackStackChangedListener(
        () -> mStartActivityPresenter.setDrawerIndicator());
  }

  @Override public void showConnectErrorMessage() {
    showAlertMessage(getString(R.string.error_connection_title),
        getString(R.string.error_connection_message));
  }

  @Override public void showWrongMessage() {
    showAlertMessage(getResources().getString(R.string.dialog_error_title),
        getResources().getString(R.string.error_smth_wrong));
  }

  @Override public void logoutUser() {
    mNavigator.startActivityClearStack(this, new Intent(this, StartActivity.class));
  }

  @Override public void hideFloatingButton() {
    mFabBooking.setVisibility(View.INVISIBLE);
  }

  @Override public void showFloatingButton() {
    mFabBooking.setVisibility(View.VISIBLE);
  }



  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_start, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_share:
        mStartActivityPresenter.share();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }



  @Override public void share() {
    String appUrl = "http://www.google.com";
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, "Watch this cool app!");
    intent.putExtra(Intent.EXTRA_TEXT, appUrl);
    startActivity(Intent.createChooser(intent, "Share app URL"));
  }

  @Override public void setDrawerIndicator() {
    if (getSupportActionBar() != null && !mNavigator.isEmptyBackStack(StartActivity.this)) {
      //mToggle.setDrawerIndicatorEnabled(false);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    } else {
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      //mToggle.setDrawerIndicatorEnabled(true);
    }
  }

  @Override public void addFragmentMain() {
    mNavigator.addFragmentTag(StartActivity.this, R.id.container_main, new MainFragment(),
        Constants.FragmentTag.MAIN_FRAGMENT);
  }

  @Override public void showAlertDialog() {
    mAuthorizationDialog = DialogFactory.createAuthorizationDialogBuilder(this)
        .setNegativeButton(R.string.dialog_auth_cancel,
            (dialog, which) -> mStartActivityPresenter.cancelAlertDialog())
        .setPositiveButton(R.string.dialog_auth_yes, (dialog, which) -> {
          mAuthorizationManager.startSignInActivity(this, ThemeUtils.getThemeActionBar(mContext),
              LocaleHelper.getLanguage(this));
          mStartActivityPresenter.cancelAlertDialog();
        })
        .create();
    mAuthorizationDialog.show();
    mAuthorizationDialog.setOnCancelListener(dialog -> mStartActivityPresenter.cancelAlertDialog());
  }

  @Override public void cancelAlertDialog() {
    if (mAuthorizationDialog != null) {
      mAuthorizationDialog.dismiss();
    }
  }

  @Override public void startSignInActivity() {
    mAuthorizationManager.startSignInActivity(this, ThemeUtils.getThemeActionBar(this),
        LocaleHelper.getLanguage(this));
  }

  @Override public void onDestroy() {
    super.onDestroy();
    cancelAlertDialog();
  }
}