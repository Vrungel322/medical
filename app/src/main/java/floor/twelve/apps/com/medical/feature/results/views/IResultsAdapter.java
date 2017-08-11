package floor.twelve.apps.com.medical.feature.results.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IResultsAdapter extends MvpView {
}
