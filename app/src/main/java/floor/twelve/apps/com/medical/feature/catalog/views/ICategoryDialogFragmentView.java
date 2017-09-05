package floor.twelve.apps.com.medical.feature.catalog.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.goods.category.Genre;
import java.util.ArrayList;

/**
 * Created by Vrungel on 29.05.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface ICategoryDialogFragmentView
    extends MvpView {
  void fillCategories(ArrayList<Genre> goodsCategoryEntities);

  void stopProgressBar();

  void startProgressBar();
}
