package floor.twelve.apps.com.medical.feature.main_screen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.base.Navigator;
import floor.twelve.apps.com.medical.data.remote.LastBookingEntity;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.MyLastBookingAdapterPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.IMyLastBookingAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 10.08.2017.
 */

public class MyLastBookingAdapter
    extends MvpBaseRecyclerAdapter<MyLastBookingAdapter.MyLastBookingViewHolder>
    implements IMyLastBookingAdapterView {
  @InjectPresenter MyLastBookingAdapterPresenter mMyLastBookingAdapterPresenter;
  private final Context mContext;
  private final Navigator mNavigator;
  private ArrayList<LastBookingEntity> mLastBookingEntities = new ArrayList<>();

  public MyLastBookingAdapter(MvpDelegate<?> parentDelegate, Context context, Navigator navigator) {
    super(parentDelegate, "MyLastBookingAdapterPresenter");
    this.mContext = context;
    this.mNavigator = navigator;
  }

  public void addListLastBookingEntity(List<LastBookingEntity> lastBookingEntities) {
    mLastBookingEntities.clear();
    mLastBookingEntities.addAll(lastBookingEntities);
    notifyDataSetChanged();
  }

  @Override public MyLastBookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MyLastBookingAdapter.MyLastBookingViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_booking, parent, false));
  }

  @Override public void onBindViewHolder(MyLastBookingViewHolder holder, int position) {
    holder.mTextViewServiceType.setText(mLastBookingEntities.get(position).getServiceType());
    holder.mTextViewServiceName.setText(mLastBookingEntities.get(position).getServiceName());
    holder.mTextViewTime.setText(mLastBookingEntities.get(position).getServiceTime());
    holder.mTextViewServiceDescription.setText(
        mLastBookingEntities.get(position).getServiceDescription());
    holder.mTextViewAddress.setText(mLastBookingEntities.get(position).getAddress());
    holder.mTextViewDoctorsName.setText(mLastBookingEntities.get(position).getDoctorsName());
    holder.mTextViewDoctorsProf.setText(mLastBookingEntities.get(position).getDoctorsProf());
    holder.mTextViewComments.setText(
        "Comments " + mLastBookingEntities.get(position).getComments());
    Glide.with(mContext)
        .load(mLastBookingEntities.get(position).getDoctorsPhoto())
        .into(holder.mImageViewDoctorsPhoto);

  }

  @Override public int getItemCount() {
    return mLastBookingEntities.size();
  }

  public static class MyLastBookingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvServiceType) TextView mTextViewServiceType;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvTime) TextView mTextViewTime;
    @BindView(R.id.tvServiceDescription) TextView mTextViewServiceDescription;
    @BindView(R.id.tvAddress) TextView mTextViewAddress;
    @BindView(R.id.tvDoctorsName) TextView mTextViewDoctorsName;
    @BindView(R.id.tvDoctorsProf) TextView mTextViewDoctorsProf;
    @BindView(R.id.tvComments) TextView mTextViewComments;
    @BindView(R.id.tvPostpone) TextView mTextViewPostpone;
    @BindView(R.id.ivDoctorsPhoto) CircleImageView mImageViewDoctorsPhoto;
    @BindView(R.id.ivStatus) ImageView mImageViewStatus;
    @BindView(R.id.ivDiscard) ImageView mImageViewDiscard;

    MyLastBookingViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}

