package floor.twelve.apps.com.medical.feature.settings.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import floor.twelve.apps.com.medical.utils.DialogFactory;

import static floor.twelve.apps.com.medical.utils.Constants.Remote.PRIVACY_POLICY_URL;
import static floor.twelve.apps.com.medical.utils.Constants.Remote.TERMS_OF_SERVICE_URL;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

public class SettingsFragment extends BaseFragment implements ISettingsFragment {

  @InjectPresenter SettingsFragmentPresenter mPresenter;

  @BindView(R.id.rlNotifications) RelativeLayout mRelativeLayoutNotifications;

  private AlertDialog mChangeLanguageDialog;
  private String mSelectedLanguage;

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

  @OnClick(R.id.rlTheme) void changeTheme() {
    ThemeDialogFragment themeDialog = new ThemeDialogFragment();
    themeDialog.show(getActivity().getFragmentManager(), "");
    themeDialog.setCancelable(false);
  }

  @OnClick(R.id.tvLanguage) public void changeLanguage() {
    mPresenter.showChangeLanguageDialog();
  }

  @Override public void showChangeLanguageDialog() {
    String[] languages = getResources().getStringArray(R.array.dialog_language);
    mSelectedLanguage = languages[mPresenter.getLanguagePosition()];
    mChangeLanguageDialog = DialogFactory.createAlertDialogBuilder(getContext(),
        getString(R.string.dialog_title_select_language))
        .setSingleChoiceItems(languages, mPresenter.getLanguagePosition(),
            (dialog, which) -> mSelectedLanguage = languages[which])
        .setPositiveButton(R.string.btn_ok, (dialog, which) -> {
          mPresenter.saveLanguage(mSelectedLanguage);
          mNavigator.startActivityClearStack((AppCompatActivity) getActivity(),
              new Intent(getActivity(), StartActivity.class));
        })
        .setNegativeButton(R.string.btn_cancel,
            (dialogInterface, i) -> mPresenter.closeChangeLanguageDialog())
        .setCancelable(false)
        .create();
    mChangeLanguageDialog.setOnCancelListener(dialog -> mPresenter.closeChangeLanguageDialog());
    mChangeLanguageDialog.show();
  }

  @Override public void closeChangeLanguageDialog() {
    if (mChangeLanguageDialog != null) {
      mChangeLanguageDialog.dismiss();
    }
  }

  @OnClick(R.id.rlTermOfService) public void onRlTermOfServiceClicked() {
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(TERMS_OF_SERVICE_URL));
    startActivity(i);
  }

  @OnClick(R.id.rlPrivacyPolicy) public void onRlPrivacyPolicyClicked() {
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(PRIVACY_POLICY_URL));
    startActivity(i);
  }

  @OnClick(R.id.rlPartners) public void showPartners() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        PartnersFragment.newInstance());
  }

  @OnClick(R.id.rlAboutApplication) public void showAboutAppDialog() {
    AboutApplicationDialog aboutApplicationDialog = new AboutApplicationDialog();
    aboutApplicationDialog.show(getActivity().getFragmentManager(), "");
  }
}
