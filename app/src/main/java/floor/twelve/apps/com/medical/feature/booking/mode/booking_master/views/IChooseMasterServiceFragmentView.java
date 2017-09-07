package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterServiceFragmentView
    extends MvpView {
  void setUpRvServices();

  void updateRvServices(List<ServiceEntity> serviceEntities, String serviceId);

  void setItemSelected(int position);

  void hideProgressBar();

  void setMasterName(String masterName);
}
