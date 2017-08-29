package floor.twelve.apps.com.medical.feature.offers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import floor.twelve.apps.com.medical.feature.offers.fragmnets.AllSalesFragment;
import java.util.List;

/**
 * Created by Vrungel on 29.08.2017.
 */

public class SalesTypePagerAdapter extends FragmentStatePagerAdapter {

  private final List<String> mFragmentTitles;

  public SalesTypePagerAdapter(FragmentManager fm, List<String> fragmentsTitles) {
    super(fm);
    this.mFragmentTitles = fragmentsTitles;
  }

  @Override public Fragment getItem(int position) {
    if (position == 0) {
      return (AllSalesFragment.newInstance());
    }
    if (position == 1) {
      return (AllSalesFragment.newInstance());
    }
    return null;
  }

  @Override public CharSequence getPageTitle(int position) {
    return mFragmentTitles.get(position);
  }

  @Override public int getCount() {
    return mFragmentTitles.size();
  }
}

