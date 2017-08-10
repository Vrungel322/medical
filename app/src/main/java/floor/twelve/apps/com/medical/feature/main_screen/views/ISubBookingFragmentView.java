package floor.twelve.apps.com.medical.feature.main_screen.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import java.util.List;

/**
 * Created by Vrungel on 10.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface ISubBookingFragmentView
    extends MvpView {
  void showLastBookings(List<LastBookingEntity> lastBookingEntities);

}
