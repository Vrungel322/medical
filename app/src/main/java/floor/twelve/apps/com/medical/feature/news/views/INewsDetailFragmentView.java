package floor.twelve.apps.com.medical.feature.news.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 23.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface INewsDetailFragmentView
    extends MvpView {
}
