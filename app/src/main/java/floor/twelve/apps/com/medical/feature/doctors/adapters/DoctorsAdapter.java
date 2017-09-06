package floor.twelve.apps.com.medical.feature.doctors.adapters;

import android.content.Context;
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
import floor.twelve.apps.com.medical.data.model.DoctorEntity;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorsAdapterPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

public class DoctorsAdapter extends MvpBaseRecyclerAdapter<DoctorsAdapter.DoctorViewHolder>
    implements IDoctorsAdapterView {

  @InjectPresenter DoctorsAdapterPresenter mPresenter;

  private Context mContext;
  private ArrayList<DoctorEntity> doctorEntities = new ArrayList<>();

  public DoctorsAdapter(MvpDelegate<?> parentDelegate, Context context) {
    super(parentDelegate, "DoctorsAdapter");
    this.mContext = context;
  }

  public void addListDoctorEntities(List<DoctorEntity> list) {
    doctorEntities.clear();
    doctorEntities.addAll(list);
    notifyDataSetChanged();
  }

  @Override public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DoctorViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false));
  }

  @Override public void onBindViewHolder(DoctorViewHolder holder, int position) {
    Glide.with(holder.ivDoctorImg.getContext())
        .load(doctorEntities.get(position).getImageUrl())
        .error(R.drawable.doctor_place_holder)
        .into(holder.ivDoctorImg);
    holder.tvDoctorsName.setText(doctorEntities.get(position).getName());
    holder.ratingBar.setRating(doctorEntities.get(position).getRating());
    holder.tvDoctorsSpeciality.setText(doctorEntities.get(position).getSpeciality());
    holder.tvDoctorsRank.setText(doctorEntities.get(position).getRank());
    holder.tvReviews.setText(mContext.getString(R.string.doctors_reviews,
        doctorEntities.get(position).getReviewsCount()));
  }

  @Override public int getItemCount() {
    return doctorEntities.size();
  }

  class DoctorViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivDoctorImg) CircleImageView ivDoctorImg;
    @BindView(R.id.tvDoctorsName) TextView tvDoctorsName;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.tvDoctorsSpeciality) TextView tvDoctorsSpeciality;
    @BindView(R.id.tvDoctorsRank) TextView tvDoctorsRank;
    @BindView(R.id.tvReviews) TextView tvReviews;
    @BindView(R.id.tvBooking) TextView tvBooking;

    public DoctorViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
