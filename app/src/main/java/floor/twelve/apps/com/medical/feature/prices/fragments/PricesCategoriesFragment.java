package floor.twelve.apps.com.medical.feature.prices.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.PricesCategoryEntity;
import floor.twelve.apps.com.medical.feature.prices.adapters.PricesCategoriesAdapter;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesCategoriesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.prices.views.IPricesCategoriesFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

public class PricesCategoriesFragment extends BaseFragment
    implements IPricesCategoriesFragmentView {

  @InjectPresenter PricesCategoriesFragmentPresenter mPresenter;

  @BindView(R.id.rvPricesCategories) RecyclerView rvPricesCategories;

  private PricesCategoriesAdapter mAdapter;

  public PricesCategoriesFragment() {
    super(R.layout.fragment_prices_categories);
  }

  public static PricesCategoriesFragment newInstance() {
    Bundle args = new Bundle();
    PricesCategoriesFragment fragment = new PricesCategoriesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_prices);
    mAdapter = new PricesCategoriesAdapter(getMvpDelegate());
    rvPricesCategories.setLayoutManager(new LinearLayoutManager(getContext()));
    rvPricesCategories.setHasFixedSize(true);
    rvPricesCategories.setFocusable(false);
    rvPricesCategories.setAdapter(mAdapter);

    ItemClickSupport.addTo(rvPricesCategories)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (AppCompatActivity) getActivity(), R.id.container_main,
            PricesFragment.newInstance(mAdapter.getPricesCategoryEntities().get(position).getName(),
                mAdapter.getPricesCategoryEntities().get(position).getPriceEntities())));
  }

  @Override public void showPricesCategories(List<PricesCategoryEntity> list) {
    mAdapter.addPricesCategoryEntity(list);
  }
}
