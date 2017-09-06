package floor.twelve.apps.com.medical.feature.doctors.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import floor.twelve.apps.com.medical.feature.doctors.adapters.DoctorsServicesAdapter;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorsServicesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsServicesFragmentView;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class DoctorsServicesFragment extends BaseFragment implements IDoctorsServicesFragmentView {

  @InjectPresenter DoctorsServicesFragmentPresenter mPresenter;

  @BindView(R.id.rvDoctorsServices) RecyclerView mRvDoctorsServices;

  private DoctorsServicesAdapter mAdapter;

  public DoctorsServicesFragment() {
    super(R.layout.fragment_doctors_services);
  }

  public static DoctorsServicesFragment newInstance() {
    Bundle args = new Bundle();
    DoctorsServicesFragment fragment = new DoctorsServicesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mAdapter = new DoctorsServicesAdapter(getMvpDelegate(), getContext());

    mRvDoctorsServices.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvDoctorsServices.setHasFixedSize(true);
    mRvDoctorsServices.setAdapter(mAdapter);
  }

  @Override public void showServices(List<ServiceEntity> list) {
    mAdapter.addListServiceEntities(list);
  }
}
