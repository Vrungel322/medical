package floor.twelve.apps.com.medical.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.local.LocaleHelper;
import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import floor.twelve.apps.com.medical.feature.catalog.adapters.GoodsFavoriteListAdapter;
import floor.twelve.apps.com.medical.feature.catalog.presenters.CatalogFavoriteFragmentPresenter;
import floor.twelve.apps.com.medical.feature.catalog.views.ICataloFavoriteFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import floor.twelve.apps.com.medical.utils.ThemeUtils;
import floor.twelve.apps.com.medical.utils.ViewUtil;
import java.util.List;

/**
 * Created by John on 06.06.2017.
 */

public class CatalogFavoriteFragment extends BaseFragment implements ICataloFavoriteFragmentView {

  @InjectPresenter CatalogFavoriteFragmentPresenter mCatalogFavoriteFragmentPresenter;

  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.rvCatalogFavorite) RecyclerView mRvCatalogFavorite;
  @BindView(R.id.textViewTitle) TextView mTextViewTitle;
  @BindView(R.id.tvFavoriteEmptyList) TextView mTvFavoriteEmptyList;

  private GoodsFavoriteListAdapter mGoodsFavoriteListAdapter;

  public CatalogFavoriteFragment() {
    super(R.layout.fragment_catalog_favorite);
  }

  public static CatalogFavoriteFragment newInstance() {
    Bundle args = new Bundle();
    CatalogFavoriteFragment fragment = new CatalogFavoriteFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mGoodsFavoriteListAdapter = new GoodsFavoriteListAdapter(getContext());
    mRvCatalogFavorite.setLayoutManager(
        new GridLayoutManager(getContext(), ViewUtil.getRotation(getContext())));
    mRvCatalogFavorite.setAdapter(mGoodsFavoriteListAdapter);
    ItemClickSupport.addTo(mRvCatalogFavorite)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (StartActivity) getActivity(), R.id.container_main,
            GoodsDetailsFragment.newInstance(mGoodsFavoriteListAdapter.getEntity(position))));

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeResources(value.resourceId);
    mSwipeRefreshLayout.setOnRefreshListener(
        () -> mCatalogFavoriteFragmentPresenter.fetchFavoriteGoodsList());
  }

  @Override public void updateGoodsFavoriteList(List<GoodsEntity> goodsEntities) {
    if (goodsEntities.isEmpty()) {
      mTextViewTitle.setVisibility(View.GONE);
      mTvFavoriteEmptyList.setVisibility(View.VISIBLE);
    } else {
      mTextViewTitle.setVisibility(View.VISIBLE);
      mTvFavoriteEmptyList.setVisibility(View.GONE);
    }
    mGoodsFavoriteListAdapter.addListGoodsEntity(goodsEntities);
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void startLoginActivity() {
    mAuthorizationManager.startSignInActivity((AppCompatActivity) getActivity(),
        ThemeUtils.getThemeActionBar(getContext()), LocaleHelper.getLanguage(getContext()));
  }
}
