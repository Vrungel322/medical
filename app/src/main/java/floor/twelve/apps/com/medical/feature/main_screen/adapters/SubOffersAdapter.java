package floor.twelve.apps.com.medical.feature.main_screen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
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

  }

  @Override public int getItemCount() {
    return mOffers.size();
  }

  public static class SubOffersViewHolder extends RecyclerView.ViewHolder {

    SubOffersViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
