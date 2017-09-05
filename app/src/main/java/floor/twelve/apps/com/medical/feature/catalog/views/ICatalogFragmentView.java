package floor.twelve.apps.com.medical.feature.catalog.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import java.util.List;

/**
 * Created by John on 17.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ICatalogFragmentView
    extends MvpView {

  void updateGoodsList(List<GoodsEntity> goodsEntities);

  void startRefreshingView();

  void stopRefreshingView();

  void setCategoryTitle(String title);

  void setButtonDefaultText();

  void startLoginActivity();

  void showServerErrorMsg();

  void showResetBtn();
}
