package floor.twelve.apps.com.medical.feature.news.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import java.util.List;

/**
 * Created by Vrungel on 24.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IAllNewsFragmentView
    extends MvpView {
  void addListOfNews(List<NewsEntity> newsEntities);

  void startRefreshingView();

  void stopRefreshingView();

  void showServerErrorMsg();
}
