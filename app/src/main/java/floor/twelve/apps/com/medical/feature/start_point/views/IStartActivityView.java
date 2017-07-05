package floor.twelve.apps.com.medical.feature.start_point.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 05.07.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IStartActivityView
    extends MvpView {
}
