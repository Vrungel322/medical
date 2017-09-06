package floor.twelve.apps.com.medical.feature.doctors.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.ReviewEntity;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IDoctorsReviewsFragmentView
    extends MvpView {
  void showReview(List<ReviewEntity> list);
}
