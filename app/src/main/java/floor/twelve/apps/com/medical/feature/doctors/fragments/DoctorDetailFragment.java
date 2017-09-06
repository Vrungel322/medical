package floor.twelve.apps.com.medical.feature.doctors.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.doctors.adapters.DoctorsInfoTypePagerAdapter;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorDetailFragmentPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorDetailFragmentView;
import java.util.Arrays;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

public class DoctorDetailFragment extends BaseFragment implements IDoctorDetailFragmentView {

  @InjectPresenter DoctorDetailFragmentPresenter mPresenter;

  @BindView(R.id.ivDoctorImg) AppCompatImageView ivDoctorImg;
  @BindView(R.id.checkBoxFavorite) CheckBox checkBoxFavorite;
  @BindView(R.id.tvDoctorsName) TextView tvDoctorsName;
  @BindView(R.id.ratingBar) RatingBar ratingBar;
  @BindView(R.id.tvDoctorsSpeciality) TextView tvDoctorsSpeciality;
  @BindView(R.id.tvDoctorsRank) TextView tvDoctorsRank;
  @BindView(R.id.tabLayout) TabLayout tabLayout;
  @BindView(R.id.viewPagerDoctor) ViewPager viewPagerDoctor;

  public DoctorDetailFragment() {
    super(R.layout.fragment_doctor_detail);
  }

  public static DoctorDetailFragment newInstance() {
    Bundle args = new Bundle();
    DoctorDetailFragment fragment = new DoctorDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewPagerDoctor.setAdapter(new DoctorsInfoTypePagerAdapter(this.getChildFragmentManager(),
        Arrays.asList(getResources().getStringArray(R.array.doctor_detail_tab_types))));
    tabLayout.setupWithViewPager(viewPagerDoctor);
    viewPagerDoctor.setCurrentItem(0);
  }
}
