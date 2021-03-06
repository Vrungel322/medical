package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.DataServiceEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.adapters.DatesHorizontalAdapter;
import floor.twelve.apps.com.medical.feature.booking.mode.adapters.DatesInMonthViewPagerAdapter;
import floor.twelve.apps.com.medical.feature.booking.mode.adapters.ScheduleAdapter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.ChooseMasterTimeFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IChooseMasterTimeFragmentView;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import java.util.ArrayList;
import java.util.List;

public class ChooseMasterTimeFragment extends BaseFragment
    implements IChooseMasterTimeFragmentView {
  private static final int SCHEDULE_SPAN_COUNT = 6;
  private static final int SELECTED_ITEM_DEFAULT = -1;

  @InjectPresenter ChooseMasterTimeFragmentPresenter mChooseTimeFragmentPresenter;

  @BindView(R.id.tv_service_description) TextView mTextViewServiceName;
  @BindView(R.id.tv_master_description) TextView mTextViewMasterDescription;

  @BindView(R.id.bPrevDay) ImageView mImageViewPrevDay;
  @BindView(R.id.vpDatesOfMonth) ViewPager mViewPagerDatesOfMonth;
  @BindView(R.id.bNextDay) ImageView mImageViewNextDay;
  @BindView(R.id.rvDates) RecyclerView mRecyclerViewHorizontalDates;
  @BindView(R.id.tvDateInText) TextView mTextViewDateInText;
  @BindView(R.id.rvScheduleInDay) RecyclerView mRecyclerViewScheduleInDay;
  @BindView(R.id.relativeLayoutNotTime) RelativeLayout mRelativeLayoutNotTime;
  @BindView(R.id.linearLayoutTime) LinearLayout mLinearLayoutTime;
  @BindView(R.id.buttonBookingPhone) Button mButtonBookingPhone;
  @BindView(R.id.textViewCurrentDate) TextView mTextViewCurrentDate;
  @BindView(R.id.nestedScrollBookingTime) NestedScrollView mNestedScrollBookingTime;
  @BindView(R.id.progressBarBookingTime) ProgressBar mProgressBarBookingTime;
  @BindView(R.id.relativeLayoutDay) RelativeLayout mRelativeLayoutDay;

  private List<DataServiceEntity> mDays = new ArrayList<>();
  private DatesHorizontalAdapter mDatesHorizontalAdapter;
  private ScheduleAdapter mScheduleAdapter;
  private DatesInMonthViewPagerAdapter mDatesInMonthViewPagerAdapter;

  public ChooseMasterTimeFragment() {
    super(R.layout.fragment_choose_master_time);
  }

  public static ChooseMasterTimeFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterTimeFragment fragment = new ChooseMasterTimeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setUpUi(List<DataServiceEntity> days) {
    //viewPager
    mDays = days;
    mDatesInMonthViewPagerAdapter = new DatesInMonthViewPagerAdapter(getContext(), days);
    mViewPagerDatesOfMonth.setAdapter(mDatesInMonthViewPagerAdapter);
    mViewPagerDatesOfMonth.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        chainViewPagerRecyclerView(position);
        mScheduleAdapter.setTimeSchedule(
            mDays.get(mViewPagerDatesOfMonth.getCurrentItem()).getScheduleEntities());
        mChooseTimeFragmentPresenter.clearSelectedTime();
        mChooseTimeFragmentPresenter.setDateToTv();
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    //Horizontal RV
    mRecyclerViewHorizontalDates.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mDatesHorizontalAdapter = new DatesHorizontalAdapter(getContext());
    mRecyclerViewHorizontalDates.setAdapter(mDatesHorizontalAdapter);
    chainViewPagerRecyclerView(mViewPagerDatesOfMonth.getCurrentItem());
    ItemClickSupport.addTo(mRecyclerViewHorizontalDates)
        .setOnItemClickListener((recyclerView, position, v) -> {
          mChooseTimeFragmentPresenter.setSelectedDay(position);
          mViewPagerDatesOfMonth.setCurrentItem(position);
          checkArrows();
        });

    //Schedule RV (hours)
    mRecyclerViewScheduleInDay.setLayoutManager(
        new GridLayoutManager(getContext(), SCHEDULE_SPAN_COUNT));
    mScheduleAdapter = new ScheduleAdapter(getContext());
    mRecyclerViewScheduleInDay.setAdapter(mScheduleAdapter);
    ItemClickSupport.addTo(mRecyclerViewScheduleInDay)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mChooseTimeFragmentPresenter.setSelectedTime(position));

    mDatesHorizontalAdapter.addListWorkStartEndEntity(mDays);
    mScheduleAdapter.setTimeSchedule(
        mDays.get(mViewPagerDatesOfMonth.getCurrentItem()).getScheduleEntities());
  }

  private void chainViewPagerRecyclerView(int currentItem) {
    mDatesHorizontalAdapter.setSelectedItem(currentItem);
    mRecyclerViewHorizontalDates.smoothScrollToPosition(currentItem);
  }

  @Override public void setSelectedTime(int position) {
    mScheduleAdapter.setSelectedItem(position);
  }

  @Override public void setSelectedDay(int position) {
    mDatesHorizontalAdapter.setSelectedItem(position);
  }

  @Override public void setTextToDayTv() {
    mTextViewDateInText.setText(
        mDatesInMonthViewPagerAdapter.getEntity(mViewPagerDatesOfMonth.getCurrentItem()));
  }

  @Override public void showTimeBooking() {
    mLinearLayoutTime.setVisibility(View.VISIBLE);
  }

  @Override public void showNotTime() {
    mRelativeLayoutNotTime.setVisibility(View.VISIBLE);
    mRelativeLayoutDay.setVisibility(View.GONE);
  }

  @Override public void hideProgressBarBookingTime() {
    mProgressBarBookingTime.setVisibility(View.GONE);
    mNestedScrollBookingTime.setVisibility(View.VISIBLE);
  }

  @Override public void setUpRedSquare(String serviceName, String masterName) {
    mTextViewMasterDescription.setText(masterName);
    mTextViewServiceName.setText(serviceName);
  }

  @Override public void timeIsNotAvailable() {
    showToastMessage(R.string.error_time_not_available);
  }

  @Override public void clearSelectedTime() {
    mScheduleAdapter.setSelectedItem(SELECTED_ITEM_DEFAULT);
  }

  @OnClick(R.id.bPrevDay) public void bPrevDayClicked() {
    if (mViewPagerDatesOfMonth.getCurrentItem() > 0) {
      mViewPagerDatesOfMonth.setCurrentItem(mViewPagerDatesOfMonth.getCurrentItem() - 1);
    }
    checkArrows();
  }

  @OnClick(R.id.bNextDay) public void bNextDayClicked() {
    if (mViewPagerDatesOfMonth.getCurrentItem() < mDays.size()) {
      mViewPagerDatesOfMonth.setCurrentItem(mViewPagerDatesOfMonth.getCurrentItem() + 1);
    }
    checkArrows();
  }

  private void checkArrows() {
    if (mViewPagerDatesOfMonth.getCurrentItem() == mDays.size() - 1) {
      mImageViewNextDay.setVisibility(View.GONE);
    } else {
      mImageViewNextDay.setVisibility(View.VISIBLE);
    }
    if (mViewPagerDatesOfMonth.getCurrentItem() == 0) {
      mImageViewPrevDay.setVisibility(View.GONE);
    } else {
      mImageViewPrevDay.setVisibility(View.VISIBLE);
    }
  }

  @OnClick(R.id.buttonBookingPhone) public void onViewClicked() {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:" + mButtonBookingPhone.getText()));
    startActivity(intent);
  }
}
