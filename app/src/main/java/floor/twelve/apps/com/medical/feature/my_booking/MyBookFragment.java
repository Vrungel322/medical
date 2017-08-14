package floor.twelve.apps.com.medical.feature.my_booking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.feature.main_screen.adapters.MyLastBookingAdapter;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import java.util.List;

/**
 * Created by Vrungel on 14.08.2017.
 */

public class MyBookFragment extends BaseFragment implements IMyBookFragmentView {
  @InjectPresenter MyBookFragmentPresenter mMyBookFragmentPresenter;

  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.rvMyBook) RecyclerView mRecyclerViewMyBooks;
  @BindView(R.id.tvBookEmptyList) TextView mTextViewBookEmptyList;

  private MyLastBookingAdapter mMyLastBookingAdapter;
  private boolean fromStartActivity;

  public MyBookFragment() {
    super(R.layout.fragment_my_book);
  }

  public static MyBookFragment newInstance() {
    Bundle args = new Bundle();
    MyBookFragment fragment = new MyBookFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (getActivity() instanceof StartActivity) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_my_booking);
    } else {
      //((BookingListActivity) getActivity()).setTitleAppBar(R.string.menu_my_booking);
    }

    if (getActivity() instanceof StartActivity) {
      fromStartActivity = true;
    } else {
      fromStartActivity = false;
    }
    mMyLastBookingAdapter = new MyLastBookingAdapter(getMvpDelegate(), getContext(), mNavigator);
    mRecyclerViewMyBooks.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewMyBooks.setAdapter(mMyLastBookingAdapter);
    mRecyclerViewMyBooks.setNestedScrollingEnabled(false);
    mRecyclerViewMyBooks.setFocusable(false);

    mRecyclerViewMyBooks.setAdapter(mMyLastBookingAdapter);

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeResources(value.resourceId);
    mSwipeRefreshLayout.setOnRefreshListener(() -> mMyBookFragmentPresenter.startRefreshing());

    //ItemClickSupport.addTo(mRecyclerViewMyBooks)
    //    .setOnItemClickListener((recyclerView, position, v) -> {
    //      if (getActivity() instanceof StartActivity) {
    //        mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
    //            BookDetailsFragment.newInstance(mMyLastBookingAdapter.getEntity(position)));
    //      } else {
    //        mNavigator.addFragmentBackStack((BookingListActivity) getActivity(),
    //            R.id.container_for_list_of_booked_services,
    //            BookDetailsFragment.newInstance(mMyLastBookingAdapter.getEntity(position)));
    //      }
    //    });
  }

  @Override public void showAllBooking(List<LastBookingEntity> bookingEntities) {
    mMyLastBookingAdapter.addListLastBookingEntity(bookingEntities);
    if (!bookingEntities.isEmpty()) {
      mTextViewBookEmptyList.setVisibility(View.GONE);
    } else {
      mTextViewBookEmptyList.setVisibility(View.VISIBLE);
    }
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showServerErrorMsg() {
    showLongAlertMessage(getString(R.string.dialog_error_title), getString(R.string.server_error));
  }
}
