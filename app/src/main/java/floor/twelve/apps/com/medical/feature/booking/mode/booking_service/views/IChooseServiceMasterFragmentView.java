package floor.twelve.apps.com.medical.feature.booking.mode.booking_service.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.MasterEntity;
import java.util.List;

/**
 * Created by Vrungel on 28.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseServiceMasterFragmentView
    extends MvpView {
  void setUpUi();

  void showMasters(List<MasterEntity> masterEntities);

  //void showDoctors(List<DoctorEntity> list);

  void hideProgressBar();

  void setSelectedItem(int position);

  void setUpRedSquare(String serviceName, String serviceTime, String serviceDuration);

  void setSelectedItem(String masterId);
}
