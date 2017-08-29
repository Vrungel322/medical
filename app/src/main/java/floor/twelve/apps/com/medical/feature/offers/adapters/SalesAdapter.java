package floor.twelve.apps.com.medical.feature.offers.adapters;

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
import floor.twelve.apps.com.medical.data.model.SalesEntity;
import floor.twelve.apps.com.medical.feature.offers.views.ISalesAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 29.08.2017.
 */

public class SalesAdapter extends MvpBaseRecyclerAdapter<SalesAdapter.SalesViewHolder>
    implements ISalesAdapterView {

  private List<SalesEntity> mSalesEntities = new ArrayList<>();

  public SalesAdapter(MvpDelegate<?> parentDelegate) {
    super(parentDelegate, "SalesAdapter");
  }

  public void addListSalesEntity(List<SalesEntity> salesEntities) {
    mSalesEntities.clear();
    mSalesEntities.addAll(salesEntities);
    notifyDataSetChanged();
  }

  @Override public SalesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SalesAdapter.SalesViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales, parent, false));
  }

  @Override public void onBindViewHolder(SalesViewHolder holder, int position) {
    Glide.with(holder.mImageViewLogo.getContext())
        .load(mSalesEntities.get(position).getImgUrl())
        .placeholder(AppCompatResources.getDrawable(holder.mImageViewLogo.getContext(),
            R.drawable.ic_news_placeholder_130dp))
        .dontAnimate()
        .into(holder.mImageViewLogo);

    holder.mTextViewDate.setText(mSalesEntities.get(position).getDate());
    holder.mTextViewTitle.setText(mSalesEntities.get(position).getTitle());
    //holder.mTextViewDetails.setText(mSalesEntities.get(position).getTitle());
    holder.mTextViewPeopleInvolved.setText(mSalesEntities.get(position).getPeopleInvolved());
    holder.mTextViewViews.setText(mSalesEntities.get(position).getViews());
    holder.mTextViewRemainTime.setText(mSalesEntities.get(position).getTimeRemain());
  }

  @Override public int getItemCount() {
    return mSalesEntities.size();
  }

  public class SalesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivLogo) ImageView mImageViewLogo;
    @BindView(R.id.tvDate) TextView mTextViewDate;
    @BindView(R.id.tvTitle) TextView mTextViewTitle;
    @BindView(R.id.tvDetail) TextView mTextViewDetails;
    @BindView(R.id.tvPeopleInvolved) TextView mTextViewPeopleInvolved;
    @BindView(R.id.tvViews) TextView mTextViewViews;
    @BindView(R.id.tvRemainTime) TextView mTextViewRemainTime;

    public SalesViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
