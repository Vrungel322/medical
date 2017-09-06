package floor.twelve.apps.com.medical.feature.doctors.fragments;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorsInfoFragmentPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsInfoFragmentView;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class DoctorsInfoFragment extends BaseFragment implements IDoctorsInfoFragmentView {

  @InjectPresenter DoctorsInfoFragmentPresenter mPresenter;

  public DoctorsInfoFragment() {
    super(R.layout.fragment_doctors_info);
  }

  public static DoctorsInfoFragment newInstance() {
    Bundle args = new Bundle();
    DoctorsInfoFragment fragment = new DoctorsInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }
}
