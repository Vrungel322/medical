package floor.twelve.apps.com.medical.feature.photo_gallery.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.photo_gallery.views.IPhotoGalleryFragmentView;
import floor.twelve.apps.com.medical.utils.ThreadSchedulers;
import rx.Subscription;

/**
 * Created by Vrungel on 31.08.2017.
 */
@InjectViewState public class PhotoGalleryFragmentPresenter
    extends BasePresenter<IPhotoGalleryFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fillPhotoGalleries();
  }

  private void fillPhotoGalleries() {
    Subscription subscription = mDataManager.fetchGalleries()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(photoGalleryEntities -> {
          getViewState().showPhotoGalleries(photoGalleryEntities);
        });
    addToUnsubscription(subscription);
  }
}
