package floor.twelve.apps.com.medical.feature.main_screen.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 09.08.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMainFragmentView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void addSubOffersAndLastResults();

  void stopRefreshingView();

  void startRefreshingView();
}