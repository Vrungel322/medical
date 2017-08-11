package floor.twelve.apps.com.medical.feature.results.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.ResultEntity;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IResultsFragment extends MvpView {
  void showResults(List<ResultEntity> resultEntities);
}
