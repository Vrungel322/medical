package floor.twelve.apps.com.medical.feature.settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by John on 29.06.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IThemeDialogFragmentView
    extends MvpView {

  void showSetThemeDialog(int position);

  void showThemeApp(int positionTheme);
}
