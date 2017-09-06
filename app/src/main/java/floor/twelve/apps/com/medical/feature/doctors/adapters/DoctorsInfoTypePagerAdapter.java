package floor.twelve.apps.com.medical.feature.doctors.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import floor.twelve.apps.com.medical.feature.doctors.fragments.DoctorsInfoFragment;
import floor.twelve.apps.com.medical.feature.doctors.fragments.DoctorsReviewsFragment;
import floor.twelve.apps.com.medical.feature.doctors.fragments.DoctorsServicesFragment;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class DoctorsInfoTypePagerAdapter extends FragmentStatePagerAdapter {
  private final List<String> mFragmentTitles;

  public DoctorsInfoTypePagerAdapter(FragmentManager fm, List<String> fragmentsTitles) {
    super(fm);
    this.mFragmentTitles = fragmentsTitles;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return DoctorsInfoFragment.newInstance();
      case 1:
        return DoctorsServicesFragment.newInstance();
      case 2:
        return DoctorsReviewsFragment.newInstance();
      default:
        return DoctorsInfoFragment.newInstance();
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    return mFragmentTitles.get(position);
  }

  @Override public int getCount() {
    return mFragmentTitles.size();
  }
}
