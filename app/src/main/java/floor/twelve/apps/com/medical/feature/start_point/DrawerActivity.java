package floor.twelve.apps.com.medical.feature.start_point;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import butterknife.BindView;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseActivity;

/**
 * Created by Vrungel on 09.08.2017.
 */

abstract public class DrawerActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
  @BindView(R.id.navigation_drawer_topPart) NavigationView mNavViewTopPart;
  @BindView(R.id.navigation_drawer_bottomPart) NavigationView mNavViewBottomPart;

  private ActionBarDrawerToggle mToggle;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setUpUI();
  }

  private void setUpUI() {
    setSupportActionBar(mToolbar);

    getSupportActionBar().setTitle(Html.fromHtml("<font color='#67b3f1'>ActionBarTitle </font>"));
    mToggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(mToggle);
    mToggle.syncState();

    mNavViewTopPart.setNavigationItemSelectedListener(this);
    mNavViewBottomPart.setNavigationItemSelectedListener(this);
    mToggle.setToolbarNavigationClickListener(v -> onBackPressed());
  }

  @Override protected void onResume() {
    super.onResume();

    if (mAuthorizationManager.isAuthorized()) {
      mNavViewTopPart.getMenu().getItem(2).setCheckable(true);
      mNavViewTopPart.getMenu().getItem(3).setCheckable(true);
    } else {
      mNavViewTopPart.getMenu().getItem(2).setCheckable(false);
      mNavViewTopPart.getMenu().getItem(3).setCheckable(false);
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_main:
        setTitleAppBar(R.string.title_activity_start);
        mNavigator.clearBackStack(this);
        break;
      case R.id.menu_booking:
        //mNavigator.startActivity(StartActivity.this,
        //    new Intent(StartActivity.this, BookingActivity.class));
        break;
      case R.id.menu_my_booking:
        if (mAuthorizationManager.isAuthorized()) {
          //mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
          //    MyBookFragment.newInstance(), Constants.FragmentTag.MY_BOOK_FRAGMENT);
        } else {
          //mStartActivityPresenter.showAlertDialog();
        }
        break;
      case R.id.menu_results:
        if (mAuthorizationManager.isAuthorized()) {
          //mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
          //    MyBonusFragment.newInstance(), Constants.FragmentTag.MY_BONUS_FRAGMENT);
        } else {
          //mStartActivityPresenter.showAlertDialog();
        }
        break;
      case R.id.menu_prices:
        //mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
        //    OurWorkFragment.newInstance(), Constants.FragmentTag.OUR_WORK_FRAGMENT);
        break;
      case R.id.menu_sales:
        //mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
        //    CatalogFragment.newInstance(), Constants.FragmentTag.CATALOG_FRAGMENT);
        break;
      case R.id.menu_doctors:
        //mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
        //    AllNewsViewFragment.newInstance(), Constants.FragmentTag.ALL_NEWS_FRAGMENT);
        break;
      case R.id.menu_news:
        //mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
        //    ContactsFragment.newInstance(), Constants.FragmentTag.CONTACTS_FRAGMENT);
        break;
      case R.id.menu_about:
        //mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
        //    ContactsFragment.newInstance(), Constants.FragmentTag.CONTACTS_FRAGMENT);
        break;
      case R.id.nav_settings:
        //mNavigator.startActivity(StartActivity.this,
        //    new Intent(StartActivity.this, SettingsActivity.class));
        break;
    }
    mDrawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      if (!mNavigator.isEmptyBackStack(DrawerActivity.this)) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        AppBarLayout appBarLayout = (AppBarLayout) this.findViewById(R.id.appBar);
        appBarLayout.setExpanded(true, true);
      }
      if (mNavigator.isOneFragmentBackStack(this)) {
        setTitleAppBar(R.string.title_activity_start);
        mNavViewTopPart.getMenu().getItem(0).setChecked(true);
      }
      super.onBackPressed();
    }
  }
}
