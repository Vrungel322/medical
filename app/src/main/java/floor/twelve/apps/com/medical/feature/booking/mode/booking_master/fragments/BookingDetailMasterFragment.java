package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.BookingDetailMasterFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IBookingDetailMasterFragmentView;
import floor.twelve.apps.com.medical.utils.Constants;
import floor.twelve.apps.com.medical.utils.ViewUtil;

/**
 * Created by John on 04.05.2017.
 */

public class BookingDetailMasterFragment extends BaseFragment
    implements IBookingDetailMasterFragmentView {

  @InjectPresenter BookingDetailMasterFragmentPresenter mBookingDetailMasterFragmentPresenter;

  @BindView(R.id.textAccentMaster) TextView mTextAccentMaster;
  @BindView(R.id.viewAccentMaster) View mViewAccentMaster;
  @BindView(R.id.tabMaster) LinearLayout mTabMaster;
  @BindView(R.id.textAccentService) TextView mTextAccentService;
  @BindView(R.id.viewAccentService) View mViewAccentService;
  @BindView(R.id.tabService) LinearLayout mTabService;
  @BindView(R.id.textAccentTime) TextView mTextAccentTime;
  @BindView(R.id.viewAccentTime) View mViewAccentTime;
  @BindView(R.id.tabTime) LinearLayout mTabTime;
  @BindView(R.id.textAccentData) TextView mTextAccentData;
  @BindView(R.id.viewAccentData) View mViewAccentData;
  @BindView(R.id.tabData) LinearLayout mTabData;

  public BookingDetailMasterFragment() {
    super(R.layout.fragment_booking_detail_master);
  }

  public static BookingDetailMasterFragment newInstance() {
    Bundle args = new Bundle();
    BookingDetailMasterFragment fragment = new BookingDetailMasterFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTabMaster.setOnClickListener(v -> {
      mBookingDetailMasterFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT);
      mNavigator.clearBackStackWithCountFragment((AppCompatActivity) getActivity(), 2);
    });

    mTabService.setOnClickListener(v -> {
      mBookingDetailMasterFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT);
      mNavigator.clearBackStackWithCountFragment((AppCompatActivity) getActivity(), 3);
    });

    mTabTime.setOnClickListener(v -> {
      mBookingDetailMasterFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT);
      mNavigator.clearBackStackWithCountFragment((AppCompatActivity) getActivity(), 4);
    });

    mTabData.setOnClickListener(v -> mBookingDetailMasterFragmentPresenter.clickTab(
        Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT));
  }

  @Override public void addFirstFragment() {
    mNavigator.addFragmentTagBackStack((AppCompatActivity) getActivity(),
        R.id.container_booking_detail_master, ChooseMasterMasterFragment.newInstance(),
        Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT);
  }

  @Override public void showMessageWarning(int warning) {
    showAlertMessage(getString(R.string.title_write_error), getString(warning));
  }

  @Override public void hideKeyboard() {
    ViewUtil.hideKeyboard(getActivity());
  }

  @Override public void goNextFragment(String fragmentTag) {
    switch (fragmentTag) {
      case Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT:
        mBookingDetailMasterFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT);
        break;
      case Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT:
        mNavigator.addFragmentTagBackStackNotCopy((AppCompatActivity) getActivity(),
            R.id.container_booking_detail_master, ChooseMasterServiceFragment.newInstance(),
            Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT);
        mBookingDetailMasterFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT);
        break;
      case Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT:
        mNavigator.addFragmentTagBackStackNotCopy((AppCompatActivity) getActivity(),
            R.id.container_booking_detail_master, ChooseMasterTimeFragment.newInstance(),
            Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT);
        mBookingDetailMasterFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT);
        break;
      case Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT:
        mNavigator.addFragmentTagBackStackNotCopy((AppCompatActivity) getActivity(),
            R.id.container_booking_detail_master, ChooseMasterContactFragment.newInstance(),
            Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT);
        mBookingDetailMasterFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT);
        break;
    }
  }

  @Override public void setSelectedTab(String fragmentTag) {
    mViewAccentMaster.setVisibility(View.INVISIBLE);
    mViewAccentService.setVisibility(View.INVISIBLE);
    mViewAccentTime.setVisibility(View.INVISIBLE);
    mViewAccentData.setVisibility(View.INVISIBLE);
    switch (fragmentTag) {
      case Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT:
        mViewAccentMaster.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn).duration(700).playOn(mViewAccentMaster);
        break;
      case Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT:
        mViewAccentService.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn).duration(700).playOn(mViewAccentService);
        break;
      case Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT:
        mViewAccentTime.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn).duration(700).playOn(mViewAccentTime);
        break;
      case Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT:
        mViewAccentData.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn).duration(700).playOn(mViewAccentData);
        break;
    }
  }

  @Override public void stateBackBookingMaster() {
    if (mNavigator.isFragmentTag((AppCompatActivity) getActivity(),
        Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT)) {
      mBookingDetailMasterFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT);
      return;
    }
    if (mNavigator.isFragmentTag((AppCompatActivity) getActivity(),
        Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT)) {
      mBookingDetailMasterFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT);
      return;
    }
    if (mNavigator.isFragmentTag((AppCompatActivity) getActivity(),
        Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT)) {
      mBookingDetailMasterFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT);
    }
  }
}
