package floor.twelve.apps.com.medical.feature.doctors.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.data.model.ReviewEntity;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorsReviewsAdapterPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsReviewsAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class DoctorsReviewsAdapter
    extends MvpBaseRecyclerAdapter<DoctorsReviewsAdapter.DoctorsReviewViewHolder>
    implements IDoctorsReviewsAdapterView {

  @InjectPresenter DoctorsReviewsAdapterPresenter mPresenter;

  private ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();

  public DoctorsReviewsAdapter(MvpDelegate<?> parentDelegate) {
    super(parentDelegate, "DoctorsReviewsAdapter");
  }

  public void addListReviewEntities(List<ReviewEntity> list) {
    reviewEntities.clear();
    reviewEntities.addAll(list);
    notifyDataSetChanged();
  }

  @Override public DoctorsReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DoctorsReviewViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_doctors_review, parent, false));
  }

  @Override public void onBindViewHolder(DoctorsReviewViewHolder holder, int position) {
    Glide.with(holder.ivUserImg.getContext())
        .load(reviewEntities.get(position).getImageUrl())
        .error(R.drawable.doctor_place_holder)
        .into(holder.ivUserImg);
    holder.ratingBar.setRating(Float.parseFloat(reviewEntities.get(position).getRating()));
    holder.tvTime.setText(reviewEntities.get(position).getDate());
    holder.tvUserName.setText(reviewEntities.get(position).getUserName());
    holder.tvMessage.setText(reviewEntities.get(position).getMessage());
    if (position != reviewEntities.size() - 1) {
      holder.divider.setVisibility(View.VISIBLE);
    }
  }

  @Override public int getItemCount() {
    return reviewEntities.size();
  }

  class DoctorsReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivUserImg) CircleImageView ivUserImg;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.tvUserName) TextView tvUserName;
    @BindView(R.id.tvTime) TextView tvTime;
    @BindView(R.id.tvMessage) TextView tvMessage;
    @BindView(R.id.divider) View divider;

    public DoctorsReviewViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
