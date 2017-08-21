package floor.twelve.apps.com.medical.feature.settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.PartnerEntity;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IPartnersFragment
    extends MvpView {
  void showPartners(List<PartnerEntity> partnerEntityList);
}
