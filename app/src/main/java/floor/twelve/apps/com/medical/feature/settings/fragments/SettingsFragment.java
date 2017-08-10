package floor.twelve.apps.com.medical.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.authorization.data.local.LocaleHelper;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.settings.presenters.SettingsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.settings.views.ISettingsFragment;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

public class SettingsFragment extends BaseFragment implements ISettingsFragment {

  @InjectPresenter SettingsFragmentPresenter mPresenter;

  @BindView(R.id.rlNotifications) RelativeLayout mRelativeLayoutNotifications;

  public SettingsFragment() {
    super(R.layout.fragment_settings);
  }

  public static SettingsFragment getInstance() {
    Bundle bundle = new Bundle();
    SettingsFragment fragment = new SettingsFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (!mAuthorizationManager.isAuthorized()) {
      mRelativeLayoutNotifications.setVisibility(View.GONE);
    }
  }

  @Override public void addUserProfileFragment() {
    mNavigator.addChildFragmentBackStack(this, R.id.container_info_user,
        AuthorizationManager.getInstance()
            .openUserProfileFragment(R.id.container_settings,
                LocaleHelper.getLanguage(getContext())));
  }

  @OnClick(R.id.rlNotifications) void editNotifications() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        NotificationSettingsFragment.newInstance());
  }

  @OnClick(R.id.rlProblem) void problems() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ReportProblemFragment.newInstance());
  }
}
