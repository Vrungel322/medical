package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.adapters.ServicesAdapter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.ChooseMasterServiceFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views.IChooseMasterServiceFragmentView;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import java.util.List;

public class ChooseMasterServiceFragment extends BaseFragment
    implements IChooseMasterServiceFragmentView {

  @InjectPresenter ChooseMasterServiceFragmentPresenter mChooseMasterServiceFragmentPresenter;

  @BindView(R.id.tv_master_description) TextView mTextViewMasterDescription;
  @BindView(R.id.etChooseService) EditText mEditTextChooseService;
  @BindView(R.id.rvServices) RecyclerView mRecyclerViewServices;
  @BindView(R.id.progressBarChooseService) ProgressBar mProgressBar;

  private ServicesAdapter mServicesAdapter;

  public ChooseMasterServiceFragment() {
    super(R.layout.fragment_choose_master_service);
  }

  public static ChooseMasterServiceFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterServiceFragment fragment = new ChooseMasterServiceFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setUpRvServices() {
    mRecyclerViewServices.setLayoutManager(new LinearLayoutManager(getContext()));
    mServicesAdapter = new ServicesAdapter(getContext());
    mRecyclerViewServices.setAdapter(mServicesAdapter);

    ItemClickSupport.addTo(mRecyclerViewServices)
        .setOnItemClickListener((recyclerView, position, v) -> {
          mChooseMasterServiceFragmentPresenter.clearLastBookingEntity();
          mChooseMasterServiceFragmentPresenter.setItemSelected(position);
        });

    mEditTextChooseService.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() != 0) {
          mChooseMasterServiceFragmentPresenter.filterServices(
              mEditTextChooseService.getText().toString());
        }
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override public void updateRvServices(List<ServiceEntity> serviceEntities, String serviceId) {
    mServicesAdapter.setServiceEntity(serviceEntities);
    if (!serviceId.isEmpty()) {
      mServicesAdapter.setSelectedItem(serviceId);
    }
  }

  @Override public void setItemSelected(int position) {
    mServicesAdapter.setSelectedItem(position);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void setMasterName(String masterName) {
    mTextViewMasterDescription.setText(masterName);
  }
}
