package floor.twelve.apps.com.medical.feature.settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 05.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface INotificationSettingsFragmentView
    extends MvpView {
  void setUpSwitchers(boolean hourly, boolean daily, boolean night);

  void setUpDaysString(int days);

  void setUpHoursString(long hours);

  void setUpNightHours(long start, long end);

  void showPickDayDialog();

  void cancelPickDayDialog();

  void showPickHourDialog();

  void cancelPickHourDialog();

  void showPickStartNightDialog();

  void cancelPickStartNightDialog();

  void showPickEndNightDialog();

  void cancelPickEndNightDialog();
}
