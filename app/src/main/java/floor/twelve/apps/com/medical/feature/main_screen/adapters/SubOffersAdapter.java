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
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.base.Navigator;
import floor.twelve.apps.com.medical.data.remote.OfferEntity;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubOffersAdapterPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubOffersAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 11.08.2017.
 */

public class SubOffersAdapter extends MvpBaseRecyclerAdapter<SubOffersAdapter.SubOffersViewHolder>
    implements ISubOffersAdapterView {

  @InjectPresenter SubOffersAdapterPresenter mSubOffersAdapterPresenter;
  private final Context mContext;
  private final Navigator mNavigator;
  private ArrayList<OfferEntity> mOffers = new ArrayList<>();

  public SubOffersAdapter(MvpDelegate<?> parentDelegate, Context context, Navigator navigator) {
    super(parentDelegate, "SubOffersAdapter");
    this.mContext = context;
    this.mNavigator = navigator;
  }

  public void addOffersEntity(List<OfferEntity> offers) {
    mOffers.clear();
    mOffers.addAll(offers);
    notifyDataSetChanged();
  }

  @Override
  public SubOffersAdapter.SubOffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SubOffersAdapter.SubOffersViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_offer, parent, false));
  }

  @Override public void onBindViewHolder(SubOffersViewHolder holder, int position) {
    Glide.with(mContext)
        .load(mOffers.get(position).getLogoUri())
        .into(holder.mImageViewOfferLogo);
    holder.mTextViewOfferName.setText(mOffers.get(position).getOfferName());
    holder.mTextViewvOfferDescription.setText(mOffers.get(position).getOfferDescription());
    holder.mTextViewvNumPeoplePass.setText(mOffers.get(position).getNumPeoplePass());
    holder.mTextViewvNumWatches.setText(mOffers.get(position).getNumWatches());
    holder.mTextViewvNumDays.setText(mOffers.get(position).getNumDays());

  }

  @Override public int getItemCount() {
    return mOffers.size();
  }

  public static class SubOffersViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivOfferLogo) ImageView mImageViewOfferLogo;
    @BindView(R.id.tvOfferName) TextView mTextViewOfferName;
    @BindView(R.id.tvOfferDescription) TextView mTextViewvOfferDescription;
    @BindView(R.id.tvNumPeoplePass) TextView mTextViewvNumPeoplePass;
    @BindView(R.id.tvNumWatches) TextView mTextViewvNumWatches;
    @BindView(R.id.tvNumDays) TextView mTextViewvNumDays;

    SubOffersViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
