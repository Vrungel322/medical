package floor.twelve.apps.com.medical.feature.our_works.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.OurWorkEntity;
import java.util.List;

/**
 * Created by Vrungel on 21.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IOurWorkFragmentView
    extends MvpView {
  void addListOfWorks(List<OurWorkEntity> ourWorkEntities);

  void startRefreshingView();

  void stopRefreshingView();

  void startLoginActivity();

  void showServerErrorMsg();
}
