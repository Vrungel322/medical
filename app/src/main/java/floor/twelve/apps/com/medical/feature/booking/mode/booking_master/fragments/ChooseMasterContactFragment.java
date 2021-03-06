package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.ChooseMasterContactFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IChooseMasterContactFragmentView;
import floor.twelve.apps.com.medical.utils.Converters;
import floor.twelve.apps.com.medical.utils.DialogFactory;
import java.util.List;

public class ChooseMasterContactFragment extends BaseFragment
    implements IChooseMasterContactFragmentView {

  @InjectPresenter ChooseMasterContactFragmentPresenter mChooseMasterContactFragmentPresenter;

  @BindView(R.id.tv_service_description) TextView mTextViewService;
  @BindView(R.id.tv_time_description) TextView mTextViewTime;
  @BindView(R.id.tv_time_duration) TextView mTextViewDuration;
  @BindView(R.id.tv_master_description) TextView mTextViewMaster;
  @BindView(R.id.edit_name) EditText mEditTextName;
  @BindView(R.id.edit_phone) EditText mEditTextPhone;
  @BindView(R.id.tvEmptyPhone) TextView mTextViewEmptyPhone;
  @BindView(R.id.tvEmptyName) TextView mTextViewEmptyName;
  @BindView(R.id.btn_booking_contact) CircularProgressButton mBtnCreateBooking;

  public ChooseMasterContactFragment() {
    super(R.layout.fragment_choose_master_contact);
  }

  public static ChooseMasterContactFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterContactFragment fragment = new ChooseMasterContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick(R.id.btn_booking_contact) void animate() {
    mChooseMasterContactFragmentPresenter.setPersonName(mEditTextName.getText().toString());
    mChooseMasterContactFragmentPresenter.setPersonPhone(mEditTextPhone.getText().toString());
    checkIfBookOnSameDate();
  }

  private void checkIfBookOnSameDate() {
    mChooseMasterContactFragmentPresenter.checkIfBookOnSameDate();
  }

  @Override public void onResume() {
    super.onResume();
    if (mAuthorizationManager.isAuthorized()) {
      mEditTextName.setText(mAuthorizationManager.getUserName());
      if (mAuthorizationManager.getUserPhone().equals("")) {
        mChooseMasterContactFragmentPresenter.setPersonPhone();
      } else {
        mChooseMasterContactFragmentPresenter.setPersonPhone(mAuthorizationManager.getUserPhone());
      }
    }
  }

  @Override public void startAnimation() {
    mBtnCreateBooking.startAnimation();
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnCreateBooking.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnCreateBooking.revertAnimation();
  }

  @Override public void showEmptyPhoneError(boolean show) {
    if (show) {
      mTextViewEmptyPhone.setVisibility(View.VISIBLE);
    } else {
      mTextViewEmptyPhone.setVisibility(View.INVISIBLE);
    }
  }

  @Override public void showEmptyNameError(boolean show) {
    if (show) {
      mTextViewEmptyName.setVisibility(View.VISIBLE);
    } else {
      mTextViewEmptyName.setVisibility(View.INVISIBLE);
    }
  }

  @Override public void showErrorMessage(int message) {
    showAlertMessage(getString(R.string.title_write_error), getString(message));
  }

  @Override public void moveToBookingListActivity() {
    mNavigator.finishActivity((AppCompatActivity) getActivity());
    //mNavigator.startActivity((AppCompatActivity) getActivity(),
    //    new Intent(getContext(), BookingListActivity.class));
  }

  @Override public void setLastPhone(String lastPhone) {
    mEditTextPhone.setText(lastPhone);
  }

  @Override public void setUpBookingInformation(String serviceName, String serviceTime,
      String serviceDuration, String masterName) {
    mTextViewService.setText(serviceName);
    mTextViewTime.setText(serviceTime);
    mTextViewDuration.setText(serviceDuration);
    mTextViewMaster.setText(masterName);
  }

  @Override public void showDoubleCheckinTimeDialog(List<LastBookingEntity> lastBookingEntities) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < lastBookingEntities.size(); i++) {
      stringBuilder.append("\n"
          + lastBookingEntities.get(i).getServiceName()
          + " - "
          + Converters.fullDateWithTimeFromSeconds(getContext(),
          lastBookingEntities.get(i).getServiceTime().toString())
          + "\n");
    }
    DialogFactory.createDoubleCheckinTimeDialog(getActivity(), stringBuilder.toString())
        .setNegativeButton(R.string.dialog_auth_cancel, (dialog, which) -> dialog.cancel())
        .setPositiveButton(R.string.dialog_yes, (dialog, which) -> {
          dialog.cancel();
          mChooseMasterContactFragmentPresenter.sendBookingEntity();
        })
        .create()
        .show();
  }

  @Override public void checkin() {
    mChooseMasterContactFragmentPresenter.sendBookingEntity();
  }
}
