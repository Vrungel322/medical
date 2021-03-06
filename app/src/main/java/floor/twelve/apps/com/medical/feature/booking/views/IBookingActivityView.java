package floor.twelve.apps.com.medical.feature.booking.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by John on 23.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingActivityView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void addFragmentBooking();

  @StateStrategyType(SkipStrategy.class) void closeBookingService();

  void setBonusCount(int count);

  @StateStrategyType(SkipStrategy.class) void showConnectErrorMessage();

  void showAuthAlertDialog();

  void cancelAuthAlertDialog();

  void showNoInternetAlertDialog();

  void showExitAlertDialog();

  void cancelExitAlertDialog();

  @StateStrategyType(SkipStrategy.class) void logoutUser();

  @StateStrategyType(SkipStrategy.class) void startSignInActivity();

  @StateStrategyType(SkipStrategy.class) void showWrongMessage();
}
