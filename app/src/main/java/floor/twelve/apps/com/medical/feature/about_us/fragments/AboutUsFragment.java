package floor.twelve.apps.com.medical.feature.about_us.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.about_us.presenters.AboutUsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.about_us.views.IAboutUsFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;

/**
 * Created by Vrungel on 31.08.2017.
 */

public class AboutUsFragment extends BaseFragment implements IAboutUsFragmentView {

  @InjectPresenter AboutUsFragmentPresenter mPresenter;

  public static AboutUsFragment newInstance() {
    Bundle args = new Bundle();
    AboutUsFragment fragment = new AboutUsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public AboutUsFragment() {
    super(R.layout.fragment_about_us);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_photogallery);
  }
}
