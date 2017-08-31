package floor.twelve.apps.com.medical.feature.photo_gallery.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import floor.twelve.apps.com.medical.data.model.PhotoGalleryEntity;
import java.util.List;

/**
 * Created by Vrungel on 31.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IPhotoGalleryFragmentView
    extends MvpView {
  void showPhotoGalleries(List<PhotoGalleryEntity> photoGalleryEntities);
}
