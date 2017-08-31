package floor.twelve.apps.com.medical.feature.photo_gallery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.PhotoGalleryEntity;
import floor.twelve.apps.com.medical.feature.photo_gallery.adapters.PhotoGalleryAdapter;
import floor.twelve.apps.com.medical.feature.photo_gallery.presenters.PhotoGalleryFragmentPresenter;
import floor.twelve.apps.com.medical.feature.photo_gallery.views.IPhotoGalleryFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import java.util.List;

/**
 * Created by Vrungel on 31.08.2017.
 */

public class PhotoGalleryFragment extends BaseFragment implements IPhotoGalleryFragmentView {

  @InjectPresenter PhotoGalleryFragmentPresenter mPresenter;

  @BindView(R.id.rvPhotogallery) RecyclerView mRecyclerViewPhotoGallery;
  private PhotoGalleryAdapter mAdapter;

  public static PhotoGalleryFragment newInstance() {
    Bundle args = new Bundle();
    PhotoGalleryFragment fragment = new PhotoGalleryFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public PhotoGalleryFragment() {
    super(R.layout.fragment_photogallery);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_photogallery);
    mAdapter = new PhotoGalleryAdapter(getMvpDelegate());
    mRecyclerViewPhotoGallery.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mRecyclerViewPhotoGallery.setHasFixedSize(true);
    mRecyclerViewPhotoGallery.setFocusable(false);
    mRecyclerViewPhotoGallery.setAdapter(mAdapter);
  }

  @Override public void showPhotoGalleries(List<PhotoGalleryEntity> photoGalleryEntities) {
    mAdapter.addPhotoGalleryEntity(photoGalleryEntities);
  }
}
