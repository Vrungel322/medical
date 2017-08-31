package floor.twelve.apps.com.medical.feature.prices.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.authorization.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.PriceEntity;
import floor.twelve.apps.com.medical.feature.prices.adapters.PricesAdapter;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesFragmentView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

public class PricesFragment extends BaseFragment implements IPricesFragmentView {

  @InjectPresenter PricesFragmentPresenter mPresenter;

  @BindView(R.id.rvPrices) RecyclerView rvPrices;
  @BindView(R.id.tvPriceCategory) TextView tvPriceCategory;

  private PricesAdapter mAdapter;

  public PricesFragment() {
    super(R.layout.fragment_prices);
  }

  public static PricesFragment newInstance(String priceCategory, ArrayList<PriceEntity> list) {
    Bundle args = new Bundle();
    args.putString(Constants.FragmentsArgumentKeys.PRICE_CATEGORY_KEY, priceCategory);
    args.putParcelableArrayList(Constants.FragmentsArgumentKeys.PRICES_KEY, list);
    PricesFragment fragment = new PricesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    tvPriceCategory.setText(
        getArguments().getString(Constants.FragmentsArgumentKeys.PRICE_CATEGORY_KEY));

    mAdapter = new PricesAdapter(getMvpDelegate(), getContext(),
        getArguments().getParcelableArrayList(Constants.FragmentsArgumentKeys.PRICES_KEY));
    rvPrices.setLayoutManager(new LinearLayoutManager(getContext()));
    rvPrices.setHasFixedSize(true);
    rvPrices.setFocusable(false);
    rvPrices.setAdapter(mAdapter);
  }

  @Override public void addListPricesEntities(List<PriceEntity> list) {
    mAdapter.addListPriceEntities(list);
  }
}
