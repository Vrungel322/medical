package floor.twelve.apps.com.medical.feature.prices.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.data.model.PricesCategoryEntity;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesCategoriesAdapterPresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesCategoriesAdapterPresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

public class PricesCategoriesAdapter
    extends MvpBaseRecyclerAdapter<PricesCategoriesAdapter.PricesCategoriesViewHolder>
    implements IPricesCategoriesAdapterPresenter {

  @InjectPresenter PricesCategoriesAdapterPresenter mPresenter;

  private ArrayList<PricesCategoryEntity> mPricesCategoryEntities = new ArrayList<>();

  public PricesCategoriesAdapter(MvpDelegate<?> parentDelegate) {
    super(parentDelegate, "PricesCategoriesAdapter");
  }

  public void addPricesCategoryEntity(List<PricesCategoryEntity> list) {
    mPricesCategoryEntities.clear();
    mPricesCategoryEntities.addAll(list);
    notifyDataSetChanged();
  }

  @Override public PricesCategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PricesCategoriesViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_prices_category, parent, false));
  }

  @Override public void onBindViewHolder(PricesCategoriesViewHolder holder, int position) {
    holder.tvCategoryName.setText(mPricesCategoryEntities.get(position).getName());
  }

  @Override public int getItemCount() {
    return mPricesCategoryEntities.size();
  }

  static class PricesCategoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvCategoryName) TextView tvCategoryName;

    public PricesCategoriesViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
