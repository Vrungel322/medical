package floor.twelve.apps.com.medical.feature.booking.mode.booking_service.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.DataServiceEntity;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseServiceTimeFragmentView
    extends MvpView {

  void setUpUi(List<DataServiceEntity> days);

  void setSelectedTime(int position);

  void setSelectedDay(int position);

  void setTextToDayTv();

  void showTimeBooking();

  void showNotTime();

  void hideProgressBarBookingTime();

  void clearSelectedTime();

  void setServiceName(String serviceName);

  void timeIsNotAvailable();
}
