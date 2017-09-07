package floor.twelve.apps.com.medical.feature.doctors.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alexandersvyatetsky on 7/09/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IFeedbackDialogView
    extends MvpView {
}
