package floor.twelve.apps.com.medical.feature.offers.fragmnets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.BindView;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.offers.adapters.SalesTypePagerAdapter;
import floor.twelve.apps.com.medical.feature.offers.views.ISalesFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import java.util.Arrays;

/**
 * Created by Vrungel on 29.08.2017.
 */

public class SalesFragment extends BaseFragment implements ISalesFragmentView {
  @BindView(R.id.ViewPagerOffers) ViewPager mViewPagerOffers;
  @BindView(R.id.tabLayout) TabLayout mTabLayout;

  public SalesFragment() {
    super(R.layout.fragment_sales);
  }

  public static SalesFragment newInstance() {
    Bundle args = new Bundle();
    SalesFragment fragment = new SalesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (getActivity() instanceof StartActivity) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_sales);
    }

    mViewPagerOffers.setAdapter(new SalesTypePagerAdapter(this.getChildFragmentManager(),
        Arrays.asList(getResources().getStringArray(R.array.sales_types))));
    mTabLayout.setupWithViewPager(mViewPagerOffers);
    mViewPagerOffers.setCurrentItem(0);
  }
}
