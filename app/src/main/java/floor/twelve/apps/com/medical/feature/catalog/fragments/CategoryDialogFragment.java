package floor.twelve.apps.com.medical.feature.catalog.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.data.model.goods.category.Genre;
import floor.twelve.apps.com.medical.data.model.goods.category.GoodsSubCategoryEntity;
import floor.twelve.apps.com.medical.feature.catalog.adapters.CategoryAdapter;
import floor.twelve.apps.com.medical.feature.catalog.presenters.CategoryDialogFragmentPresenter;
import floor.twelve.apps.com.medical.feature.catalog.views.ICategoryDialogFragmentView;
import floor.twelve.apps.com.medical.utils.NetworkUtil;
import java.util.ArrayList;

/**
 * Created by Vrungel on 29.05.2017.
 */

public class CategoryDialogFragment extends MvpDialogFragment
    implements ICategoryDialogFragmentView {

  @InjectPresenter CategoryDialogFragmentPresenter mCategoryDialogFragmentPresenter;

  @BindView(R.id.rvCategories) RecyclerView mRecyclerViewCategories;
  @BindView(R.id.progressBar) ProgressBar mProgressBar;

  private Unbinder mUnbinder;
  private CategoryAdapter mAdapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_category_dialog, container, false);
    mUnbinder = ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    return new Dialog(getActivity(), R.style.ThemeDialog_Catalog);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    RecyclerView.ItemAnimator animator = mRecyclerViewCategories.getItemAnimator();
    if (animator instanceof DefaultItemAnimator) {
      ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
    }
    mRecyclerViewCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
  }

  @Override public void fillCategories(ArrayList<Genre> genres) {

    mAdapter = new CategoryAdapter(genres);
    mRecyclerViewCategories.setAdapter(mAdapter);

    mAdapter.setChildClickListener((v, checked, group, childIndex) -> {
      mCategoryDialogFragmentPresenter.postToShowResetBtn();

      if (NetworkUtil.isNetworkConnected(getActivity())) {
        mCategoryDialogFragmentPresenter.postEventToReloadList(
            ((GoodsSubCategoryEntity) group.getItems().get(childIndex)).getId(),
            ((GoodsSubCategoryEntity) group.getItems().get(childIndex)).getTitle());
      } else {
        mCategoryDialogFragmentPresenter.postEventToReloadListLocally(
            ((GoodsSubCategoryEntity) group.getItems().get(childIndex)).getId(),
            ((GoodsSubCategoryEntity) group.getItems().get(childIndex)).getTitle());
      }
      this.dismiss();
    });
  }

  @Override public void stopProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void startProgressBar() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void onDestroyView() {
    mUnbinder.unbind();
    super.onDestroyView();
  }
}
