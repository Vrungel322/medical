package floor.twelve.apps.com.medical.feature.prices.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.data.model.PriceEntity;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesAdapterPresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 31/08/17.
 */

public class PricesAdapter extends MvpBaseRecyclerAdapter<PricesAdapter.PricesViewHolder>
    implements IPricesAdapterView {

  @InjectPresenter PricesAdapterPresenter mPresenter;

  private Context mContext;
  private ArrayList<PriceEntity> priceEntities = new ArrayList<>();

  public PricesAdapter(MvpDelegate<?> parentDelegate, Context context,
      ArrayList<PriceEntity> list) {
    super(parentDelegate, "PricesAdapter");
    mContext = context;
    priceEntities = list;
  }

  public void addListPriceEntities(List<PriceEntity> list) {
    priceEntities.clear();
    priceEntities.addAll(list);
    notifyDataSetChanged();
  }

  @Override public PricesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PricesViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prices, parent, false));
  }

  @Override public void onBindViewHolder(PricesViewHolder holder, int position) {
    if (position == 0 || position % 2 == 0) {
      holder.llContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorWhite));
    } else {
      holder.llContainer.setBackgroundColor(
          ContextCompat.getColor(mContext, R.color.colorBackgroundGray));
    }

    holder.tvServiceName.setText(priceEntities.get(position).getServiceName());
    holder.tvPrice.setText(priceEntities.get(position).getPrice());
  }

  @Override public int getItemCount() {
    return priceEntities.size();
  }

  class PricesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvServiceName) TextView tvServiceName;
    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.llContainer) LinearLayout llContainer;

    public PricesViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}


