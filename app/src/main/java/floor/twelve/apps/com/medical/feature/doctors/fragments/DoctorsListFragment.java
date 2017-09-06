package floor.twelve.apps.com.medical.feature.doctors.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.DoctorEntity;
import floor.twelve.apps.com.medical.feature.doctors.adapters.DoctorsAdapter;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorsListFragmentPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsListFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

public class DoctorsListFragment extends BaseFragment implements IDoctorsListFragmentView {

  @InjectPresenter DoctorsListFragmentPresenter mPresenter;

  @BindView(R.id.rvDoctors) RecyclerView rvDoctors;

  private DoctorsAdapter mAdapter;

  public DoctorsListFragment() {
    super(R.layout.fragment_doctors_list);
  }

  public static DoctorsListFragment newInstance() {
    Bundle args = new Bundle();
    DoctorsListFragment fragment = new DoctorsListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_doctors);

    mAdapter = new DoctorsAdapter(getMvpDelegate(), getContext());
    rvDoctors.setLayoutManager(new LinearLayoutManager(getContext()));
    rvDoctors.setHasFixedSize(true);
    rvDoctors.setAdapter(mAdapter);

    ItemClickSupport.addTo(rvDoctors)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (AppCompatActivity) getActivity(), R.id.container_main,
            DoctorDetailFragment.newInstance()));
  }

  @Override public void showDoctors(List<DoctorEntity> list) {
    mAdapter.addListDoctorEntities(list);
  }
}
