package floor.twelve.apps.com.medical.feature.my_booking;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import java.util.List;

/**
 * Created by Vrungel on 14.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyBookFragmentView
    extends MvpView {
  void showAllBooking(List<LastBookingEntity> lastBookingEntities);

  void startRefreshingView();

  void stopRefreshingView();

  void showServerErrorMsg();
}
