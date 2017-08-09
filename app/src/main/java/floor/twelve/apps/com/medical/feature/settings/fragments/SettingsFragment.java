package floor.twelve.apps.com.medical.feature.settings.fragments;

import android.os.Bundle;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.settings.views.ISettingsFragment;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

public class SettingsFragment extends BaseFragment implements ISettingsFragment {
  public SettingsFragment() {
    super(R.layout.fragment_settings);
  }

  public static SettingsFragment getInstance() {
    Bundle bundle = new Bundle();
    SettingsFragment fragment = new SettingsFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void addUserProfileFragment() {
    // TODO: 9/08/17 хардкор языка приложения
    mNavigator.addChildFragmentBackStack(this, R.id.container_info_user,
        AuthorizationManager.getInstance().openUserProfileFragment(R.id.container_settings, "ru"));
  }
}
