package floor.twelve.apps.com.medical.feature.catalog.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.authorization.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mzelzoghbi.zgallery.CustomViewPager;
import com.mzelzoghbi.zgallery.adapters.HorizontalListAdapters;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.goods.GoodsDetailContent;
import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import floor.twelve.apps.com.medical.feature.catalog.adapters.ImageGoodsViewPagerAdapter;
import floor.twelve.apps.com.medical.feature.catalog.presenters.GoodsDetailsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.catalog.views.IStaffDetailsFragmentView;
import java.util.ArrayList;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class GoodsDetailsFragment extends BaseFragment implements IStaffDetailsFragmentView {

  @InjectPresenter GoodsDetailsFragmentPresenter mGoodsDetailsFragmentPresenter;

  @BindView(R.id.tvStaffTitle) TextView mTextViewTitle;

  @BindView(R.id.tvStaffDescription) TextView mTextViewDescription;
  @BindView(R.id.viewPagerImages) CustomViewPager mViewPagerImages;
  @BindView(R.id.recyclerViewImages) RecyclerView mRecyclerViewImages;
  @BindView(R.id.bPrevStaffImg) ImageButton mImageButtonPrevious;
  @BindView(R.id.bNextStaffImg) ImageButton mImageButtonNext;
  @BindView(R.id.ivNew) ImageView mImageViewNew;
  @BindView(R.id.ivSale) ImageView mImageViewSale;
  @BindView(R.id.tvOldPrice) TextView mTextViewOldPrice;
  @BindView(R.id.tvPrice) TextView mTextViewPrice;
  @BindView(R.id.checkBoxFavoriteGoods) AppCompatCheckBox mCheckBoxFavoriteGoods;

  private ImageGoodsViewPagerAdapter mViewPagerAdapter;
  private HorizontalListAdapters mHorizontalListAdapter;

  private GoodsEntity mGoodsEntity;

  public GoodsDetailsFragment() {
    super(R.layout.fragment_catalog_detail);
  }

  public static GoodsDetailsFragment newInstance(GoodsEntity entity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.GOODS_ENTITY_KEY, entity);
    GoodsDetailsFragment fragment = new GoodsDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mGoodsEntity = getArguments().getParcelable(Constants.FragmentsArgumentKeys.GOODS_ENTITY_KEY);

    /* turn off scrolling */
    Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(0);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    LinearLayoutManager mLayoutManager =
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

    if (mGoodsEntity != null) {
      //some TV (title and gallery description)
      mTextViewTitle.setText(mGoodsEntity.getTitle());
      mTextViewDescription.setText(Html.fromHtml(mGoodsEntity.getShortDescription()));
      mTextViewPrice.setText(mGoodsEntity.getPrice());

      // pager adapter
      mViewPagerAdapter =
          new ImageGoodsViewPagerAdapter(getContext(), mGoodsEntity.getGoodsDetailContents());
      mViewPagerImages.setAdapter(mViewPagerAdapter);

      // horizontal list adapter
      ArrayList<String> listUrlPhotos = new ArrayList<>();
      for (GoodsDetailContent goodsDetailContent : mGoodsEntity.getGoodsDetailContents()) {
        listUrlPhotos.add(goodsDetailContent.getUrlPhoto());
      }
      mHorizontalListAdapter = new HorizontalListAdapters(getActivity(), listUrlPhotos,
          pos -> mViewPagerImages.setCurrentItem(pos, true));
      mRecyclerViewImages.setLayoutManager(mLayoutManager);
      mRecyclerViewImages.setAdapter(mHorizontalListAdapter);
      mHorizontalListAdapter.notifyDataSetChanged();
      if (listUrlPhotos.size() <= 1) {
        mRecyclerViewImages.setVisibility(View.GONE);
      }

      int currentPos = 0;
      mHorizontalListAdapter.setSelectedItem(currentPos);
      mViewPagerImages.setCurrentItem(currentPos);

      //if like
      mCheckBoxFavoriteGoods.setChecked(mGoodsEntity.isFavorite());
      //if new
      if (mGoodsEntity.isNew()) {
        mImageViewNew.setVisibility(View.VISIBLE);
      }
      //if sale
      if (mGoodsEntity.isForSale()) {
        mImageViewSale.setVisibility(View.VISIBLE);
        mTextViewOldPrice.setVisibility(View.VISIBLE);
        mTextViewOldPrice.setText(mGoodsEntity.getPrice());
        mTextViewOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mTextViewPrice.setText(mGoodsEntity.getNewPrice());
      }
      updateImageInfoAndButtons();
    }

    mViewPagerImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int position) {

        mRecyclerViewImages.smoothScrollToPosition(position);
        mHorizontalListAdapter.setSelectedItem(position);
      }

      @Override public void onPageScrollStateChanged(int state) {
        updateImageInfoAndButtons();
      }
    });
  }

  private void updateImageInfoAndButtons() {
    if (mViewPagerImages.getCurrentItem() == mViewPagerAdapter.getCount() - 1) {
      mImageButtonNext.setVisibility(View.INVISIBLE);
    } else {
      mImageButtonNext.setVisibility(View.VISIBLE);
    }
    if (mViewPagerImages.getCurrentItem() == 0) {
      mImageButtonPrevious.setVisibility(View.INVISIBLE);
    } else {
      mImageButtonPrevious.setVisibility(View.VISIBLE);
    }
  }

  @OnClick(R.id.bPrevStaffImg) public void nextPicture() {
    mViewPagerImages.setCurrentItem(mViewPagerImages.getCurrentItem() - 1, true);
  }

  @OnClick(R.id.bNextStaffImg) public void previousPicture() {
    mViewPagerImages.setCurrentItem(mViewPagerImages.getCurrentItem() + 1, true);
  }

  @OnClick(R.id.checkBoxFavoriteGoods) public void onCheckFavorite() {
    if (mAuthorizationManager.isAuthorized()) {
      if (mCheckBoxFavoriteGoods.isChecked()) {
        mGoodsDetailsFragmentPresenter.addFavorite(mGoodsEntity.getId());
      } else {
        mGoodsDetailsFragmentPresenter.deleteFavorite(mGoodsEntity.getId());
      }
    } else {
      mCheckBoxFavoriteGoods.setChecked(false);
      mGoodsDetailsFragmentPresenter.showAlertDialog();
    }
  }

  @Override public void setStatusFavorite(boolean statusFavorite) {
    mGoodsEntity.setFavorite(statusFavorite);
  }

  @Override public void onDestroy() {
    /* turn on scrolling */
    Toolbar mToolbar = getActivity().findViewById(R.id.toolbar);
    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    super.onDestroy();
  }
}
