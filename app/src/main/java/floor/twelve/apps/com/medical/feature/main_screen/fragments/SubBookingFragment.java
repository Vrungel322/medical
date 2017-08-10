package floor.twelve.apps.com.medical.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.feature.main_screen.adapters.MyLastBookingAdapter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubBookingFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubBookingFragmentView;
import java.util.List;

/**
 * Created by Vrungel on 10.08.2017.
 */

public class SubBookingFragment extends BaseFragment implements ISubBookingFragmentView {
  @InjectPresenter SubBookingFragmentPresenter mSubBookingFragmentPresenter;
  @BindView(R.id.rvMyLastBooking) RecyclerView mRecyclerViewMyLastBooking;

  private MyLastBookingAdapter mMyLastBookingAdapter;


  public static SubBookingFragment newInstance() {
    Bundle args = new Bundle();
    SubBookingFragment fragment = new SubBookingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public SubBookingFragment() {
    super(R.layout.fragment_sub_booking);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mMyLastBookingAdapter = new MyLastBookingAdapter(getMvpDelegate(), getContext(), mNavigator);
    mRecyclerViewMyLastBooking.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewMyLastBooking.setAdapter(mMyLastBookingAdapter);
    mRecyclerViewMyLastBooking.setNestedScrollingEnabled(false);
    mRecyclerViewMyLastBooking.setFocusable(false);
  }

  @Override public void showLastBookings(List<LastBookingEntity> lastBookingEntities) {
    mMyLastBookingAdapter.addListLastBookingEntity(lastBookingEntities);
  }
}
