package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.MasterEntity;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterMasterFragmentView
    extends MvpView {
  void setUpUi();

  void showMasters(List<MasterEntity> masterEntities);

  void hideProgressBar();

  void setSelectedItem(int position);

  void showServerErrorMsg();
}
