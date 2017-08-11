package floor.twelve.apps.com.medical.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.MainFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.IMainFragmentView;

/**
 * Created by Vrungel on 09.08.2017.
 */

public class MainFragment extends BaseFragment implements IMainFragmentView {

  @InjectPresenter MainFragmentPresenter mMainFragmentPresenter;

  @BindView(R.id.swipeRefreshMainFragment) SwipeRefreshLayout mSwipeRefreshMainFragment;

  public MainFragment() {
    super(R.layout.fragment_main);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshMainFragment.setColorSchemeResources(value.resourceId);
    mSwipeRefreshMainFragment.setOnRefreshListener(
        () -> mMainFragmentPresenter.updateOffersAndLastResults());
  }

  @Override public void addSubOffersAndLastResults() {
    mNavigator.addChildFragment(this, R.id.subFragmentContainerBooking,
        SubBookingFragment.newInstance());
    mNavigator.addChildFragment(this, R.id.subFragmentContainerLastResults,
        SubResultsFragment.newInstance());
    mNavigator.addChildFragment(this, R.id.subFragmentContainerOffers,
        SubOffersFragment.newInstance());
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshMainFragment.isRefreshing()) mSwipeRefreshMainFragment.setRefreshing(false);
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshMainFragment.isRefreshing()) mSwipeRefreshMainFragment.setRefreshing(true);
  }
}
