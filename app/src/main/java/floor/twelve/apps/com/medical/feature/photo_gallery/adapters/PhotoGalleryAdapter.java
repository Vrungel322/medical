package floor.twelve.apps.com.medical.feature.photo_gallery.adapters;

import android.net.Uri;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpDelegate;
import com.bumptech.glide.Glide;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.data.model.PhotoGalleryEntity;
import floor.twelve.apps.com.medical.feature.photo_gallery.views.IPhotoGalleryAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 31.08.2017.
 */

public class PhotoGalleryAdapter
    extends MvpBaseRecyclerAdapter<PhotoGalleryAdapter.PhotoGalleryViewHolder>
    implements IPhotoGalleryAdapterView {

  private ArrayList<PhotoGalleryEntity> mPhotoGalleryEntities = new ArrayList<>();

  public PhotoGalleryAdapter(MvpDelegate<?> parentDelegate) {
    super(parentDelegate, "PhotoGalleryAdapter");
  }

  public void addPhotoGalleryEntity(List<PhotoGalleryEntity> list) {
    mPhotoGalleryEntities.clear();
    mPhotoGalleryEntities.addAll(list);
    notifyDataSetChanged();
  }

  @Override public PhotoGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PhotoGalleryAdapter.PhotoGalleryViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_photogallery, parent, false));
  }

  @Override public void onBindViewHolder(PhotoGalleryViewHolder holder, int position) {

    Glide.with(holder.mImageViewGalleryLogo.getContext())
        .load(Uri.parse(mPhotoGalleryEntities.get(position).getImgUrl()))
        .placeholder(AppCompatResources.getDrawable(holder.mImageViewGalleryLogo.getContext(),
            R.drawable.ic_photo_gallery_placeholder_130dp))
        .dontAnimate()
        .into(holder.mImageViewGalleryLogo);

    holder.mTextViewShortDescription.setText(mPhotoGalleryEntities.get(position).getTitle());
    holder.mTextViewNumOfImgToPreview.setText(
        String.valueOf(mPhotoGalleryEntities.get(position).getImgCount()));
  }

  @Override public int getItemCount() {
    return mPhotoGalleryEntities.size();
  }

  static class PhotoGalleryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivGalleryLogo) ImageView mImageViewGalleryLogo;
    @BindView(R.id.tvShortDescription) TextView mTextViewShortDescription;
    @BindView(R.id.tvNumOfImgToPreview) TextView mTextViewNumOfImgToPreview;

    public PhotoGalleryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
