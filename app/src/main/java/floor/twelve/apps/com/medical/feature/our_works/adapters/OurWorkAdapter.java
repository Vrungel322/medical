package floor.twelve.apps.com.medical.feature.our_works.adapters;

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
import com.bumptech.glide.Glide;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.data.model.OurWorkEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkAdapter extends RecyclerView.Adapter<OurWorkAdapter.OurWorkViewHolder> {
  private ArrayList<OurWorkEntity> mOurWorkEntities = new ArrayList<>();

  public OurWorkAdapter() {
    super();
  }

  public void addListWorkEntities(List<OurWorkEntity> ourWorkEntities) {
    mOurWorkEntities.clear();
    mOurWorkEntities.addAll(ourWorkEntities);
    notifyDataSetChanged();
  }

  @Override public OurWorkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new OurWorkViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_work, parent, false));
  }

  @Override public void onBindViewHolder(OurWorkViewHolder holder, int position) {
    if (position == 0) {
      holder.mTextViewShortDescription.setText(
          holder.mTextViewShortDescription.getContext().getString(R.string.menu_favourite));
      holder.mImageViewWorkPreview.setPadding(50, 50, 50, 50);
      holder.mImageViewWorkPreview.setImageResource(R.drawable.ic_favorite_our_work_32dp);
    } else {
      holder.mTextViewShortDescription.setText(mOurWorkEntities.get(position).getTitle());
      Glide.with(holder.mImageViewWorkPreview.getContext())
          .load(Uri.parse(mOurWorkEntities.get(position).getImageURL()))
          .placeholder(AppCompatResources.getDrawable(holder.mImageViewWorkPreview.getContext(),
              R.drawable.ic_our_work_placeholder_130dp))
          .dontAnimate()
          .into(holder.mImageViewWorkPreview);
    }

    holder.mTextViewNumOfImgToPreview.setText(
        String.valueOf(mOurWorkEntities.get(position).getImageCount()));
  }

  @Override public int getItemCount() {
    return mOurWorkEntities.size();
  }

  public OurWorkEntity getEntity(int position) {
    return mOurWorkEntities.get(position);
  }

  static class OurWorkViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivWorkPreview) ImageView mImageViewWorkPreview;
    @BindView(R.id.tvShortDescription) TextView mTextViewShortDescription;
    @BindView(R.id.tvNumOfImgToPreview) TextView mTextViewNumOfImgToPreview;

    OurWorkViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
