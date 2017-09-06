package floor.twelve.apps.com.medical.feature.doctors.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IDoctorDetailFragmentView
    extends MvpView {
}
