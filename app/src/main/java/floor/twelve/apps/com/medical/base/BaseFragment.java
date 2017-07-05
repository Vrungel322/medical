package floor.twelve.apps.com.medical.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.tapadoo.alerter.Alerter;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.R;
import javax.inject.Inject;

/**
 * Created by Alexandra on 05.07.2017.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

  @Inject protected Context mContext;
  @Inject protected Navigator mNavigator;
  @Inject protected AuthorizationManager mAuthorizationManager;

  private Unbinder mUnbinder;
  private final int mLayoutId;

  public BaseFragment(int mLayoutId) {
    this.mLayoutId = mLayoutId;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    App.getAppComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(mLayoutId, container, false);
    mUnbinder = ButterKnife.bind(this, fragmentView);
    //if (getActivity() instanceof StartActivity) {
    //  AppBarLayout appBarLayout = getActivity().findViewById(R.id.appBar);
    //  appBarLayout.setExpanded(true, false);
    //}
    return fragmentView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }

  protected void showAlertMessage(String title, String message) {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    Alerter.create(getActivity()).setDuration(3000)
        .setTitle(title)
        .setText(message)
        .setBackgroundColor(value.resourceId)
        .setOnClickListener(view -> {
        })
        .show();
  }

  protected void showToastMessage(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
  }

  protected void showToastMessage(@StringRes int id) {
    Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
  }
}
