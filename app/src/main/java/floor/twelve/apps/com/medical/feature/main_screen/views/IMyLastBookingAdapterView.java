package floor.twelve.apps.com.medical.feature.main_screen.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 10.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyLastBookingAdapterView
    extends MvpView {
}
