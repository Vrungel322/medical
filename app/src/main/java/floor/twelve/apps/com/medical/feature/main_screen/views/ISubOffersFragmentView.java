package floor.twelve.apps.com.medical.feature.main_screen.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.data.remote.OfferEntity;
import java.util.List;

/**
 * Created by Vrungel on 11.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface ISubOffersFragmentView
    extends MvpView {
  void showOffers(List<OfferEntity> offerEntities);
}
