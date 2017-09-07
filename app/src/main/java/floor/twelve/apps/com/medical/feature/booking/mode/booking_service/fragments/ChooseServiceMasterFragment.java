package floor.twelve.apps.com.medical.feature.booking.mode.booking_service.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.MasterEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.adapters.MastersVerticalAdapter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters.ChooseServiceMasterFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.views.IChooseServiceMasterFragmentView;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import floor.twelve.apps.com.medical.utils.ViewUtil;
import java.util.List;

/**
 * Created by Vrungel on 28.03.2017.
 */

public class ChooseServiceMasterFragment extends BaseFragment
    implements IChooseServiceMasterFragmentView {

  @InjectPresenter ChooseServiceMasterFragmentPresenter mChooseServiceMasterFragmentPresenter;

  @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
  @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
  @BindView(R.id.tvServiceDuration) TextView mTextViewServiceDuration;
  @BindView(R.id.rvMasters) RecyclerView mRecyclerViewMasters;
  @BindView(R.id.progressBarChooseMaster) ProgressBar mProgressBar;
  @BindView(R.id.nestedScrollChooseMaster) NestedScrollView mNestedScroll;
  @BindView(R.id.viewBlockedClickRv) View mViewBlockedClickRv;

  private MastersVerticalAdapter mMastersVerticalAdapter;

  public ChooseServiceMasterFragment() {
    super(R.layout.fragment_choose_service_master);
  }

  public static ChooseServiceMasterFragment newInstance() {
    Bundle args = new Bundle();
    ChooseServiceMasterFragment fragment = new ChooseServiceMasterFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setUpUi() {
    mRecyclerViewMasters.setLayoutManager(
        new GridLayoutManager(getContext(), ViewUtil.getRotation(getContext())));
    mMastersVerticalAdapter = new MastersVerticalAdapter(getContext());
    mRecyclerViewMasters.setAdapter(mMastersVerticalAdapter);
    mRecyclerViewMasters.setNestedScrollingEnabled(false);
    mRecyclerViewMasters.setFocusable(false);
    ItemClickSupport.addTo(mRecyclerViewMasters)
        .setOnItemClickListener((recyclerView, position, v) -> {
          if (position == 0) {
            mChooseServiceMasterFragmentPresenter.setAnyMasterSelected();
          } else {
            mChooseServiceMasterFragmentPresenter.setSelectedItem(position - 1);
          }
        });
  }

  @Override public void showMasters(List<MasterEntity> masterEntities) {
    mMastersVerticalAdapter.addListMasterEntity(masterEntities);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
    mNestedScroll.setVisibility(View.VISIBLE);
  }

  @Override public void setSelectedItem(int position) {
    mMastersVerticalAdapter.setSelectedItem(position);
  }

  @Override
  public void setUpRedSquare(String serviceName, String serviceTime, String serviceDuration) {
    mTextViewServiceName.setText(serviceName);
    mTextViewServiceTime.setText(serviceTime);
    mTextViewServiceDuration.setText(serviceDuration);
  }

  @Override public void setSelectedItem(String masterId) {
    if (!masterId.isEmpty()) {
      mMastersVerticalAdapter.setSelectedItem(masterId);
    }
  }
}
