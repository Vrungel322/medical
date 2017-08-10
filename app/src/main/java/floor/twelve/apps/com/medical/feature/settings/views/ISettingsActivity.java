package floor.twelve.apps.com.medical.feature.settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alexandersvyatetsky on 9/08/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISettingsActivity
    extends MvpView {
  @StateStrategyType(SkipStrategy.class) void addSettingsFragment();

  void logoutUser();

  void startSignInActivity();

  void showWrongMessage();
}
