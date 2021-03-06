package floor.twelve.apps.com.medical.feature.catalog.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import java.util.List;

/**
 * Created by John on 06.06.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ICataloFavoriteFragmentView
    extends MvpView {

  void startRefreshingView();

  void stopRefreshingView();

  void updateGoodsFavoriteList(List<GoodsEntity> body);

  @StateStrategyType(SkipStrategy.class) void startLoginActivity();
}
