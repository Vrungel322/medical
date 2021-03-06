package floor.twelve.apps.com.medical.feature.offers.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.SalesEntity;
import java.util.List;

/**
 * Created by Vrungel on 29.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IAllSalesFragmentView
    extends MvpView {
  void addList(List<SalesEntity> salesEntities);
}
