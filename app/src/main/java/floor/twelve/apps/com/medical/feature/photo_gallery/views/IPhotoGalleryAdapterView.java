package floor.twelve.apps.com.medical.feature.photo_gallery.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 31.08.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IPhotoGalleryAdapterView
    extends MvpView {
}
