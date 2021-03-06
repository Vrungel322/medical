package floor.twelve.apps.com.medical.feature.news.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.feature.news.adapters.AllNewsAdapter;
import floor.twelve.apps.com.medical.feature.news.presenters.AllNewsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.news.views.IAllNewsFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Vrungel on 24.02.2017.
 */

public class AllNewsViewFragment extends BaseFragment implements IAllNewsFragmentView {

  @InjectPresenter AllNewsFragmentPresenter mAllNewsFragmentPresenter;

  @BindView(R.id.rvAllNews) RecyclerView mRecyclerViewAllNews;
  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.tvNewsEmptyList) TextView mTextViewNewsEmptyList;

  private AllNewsAdapter mAllNewsAdapter;

  public AllNewsViewFragment() {
    super(R.layout.fragment_all_news);
  }

  public static AllNewsViewFragment newInstance() {
    Bundle args = new Bundle();
    AllNewsViewFragment fragment = new AllNewsViewFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_news);

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getContext(), value.resourceId));

    mAllNewsAdapter = new AllNewsAdapter(getContext());
    mRecyclerViewAllNews.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewAllNews.setAdapter(mAllNewsAdapter);
    ItemClickSupport.addTo(mRecyclerViewAllNews)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (AppCompatActivity) getActivity(), R.id.container_main,
            ListNewsDetailFragment.newInstance(mAllNewsAdapter.getAllNewsList(), position)));
    mSwipeRefreshLayout.setOnRefreshListener(() -> mAllNewsFragmentPresenter.fetchListOfNews());
  }

  @Override public void addListOfNews(List<NewsEntity> newsEntities) {
    mAllNewsAdapter.addListNewsEntity(newsEntities);
    Timber.e(String.valueOf(newsEntities.size()));
    if (newsEntities.size() != 0) {
      mTextViewNewsEmptyList.setVisibility(View.GONE);
    } else {
      mTextViewNewsEmptyList.setVisibility(View.VISIBLE);
    }
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showServerErrorMsg() {
    showLongAlertMessage(getString(R.string.dialog_error_title), getString(R.string.server_error));
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
  }
}
