package floor.twelve.apps.com.medical.feature.booking.mode.booking_service.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.CategoryEntity;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import java.util.List;

/**
 * Created by Vrungel on 29.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseServiceServiceFragmentView
    extends MvpView {
  void setUpRvAllServices();

  void updateRvAllServices(List<ServiceEntity> serviceEntities);

  void updateRvCategory(List<CategoryEntity> serviceEntities);

  void hideLLAllServices();

  void setItemSelected(int position);

  void showProgressBarAllServices();

  void hideProgressBarAllServices();

  void hideProgressBar();

  void showProgressBar();

  void setUpRvCategory();

  void showLLTreeServices();

  void showLLAllServices();

  void hideLLTreeServices();

  void setServicesWithParentId(List<ServiceEntity> serviceEntities);

  void setCategoriesWithParentId(List<CategoryEntity> categoryEntities);

  void showTextPath(String text);

  void hideTextPath();

  void stateCategoriesServices(boolean state);

  void showServerErrorMsg();
}
