package floor.twelve.apps.com.medical.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.ResultEntity;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubResultsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubResultsFragment;
import floor.twelve.apps.com.medical.feature.results.adapters.ResultsAdapter;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

public class SubResultsFragment extends BaseFragment implements ISubResultsFragment {

  @InjectPresenter SubResultsFragmentPresenter mPresenter;

  @BindView(R.id.rvResults) RecyclerView mRvResults;

  public SubResultsFragment() {
    super(R.layout.fragment_sub_results);
  }

  private ResultsAdapter mResultsAdapter;

  public static SubResultsFragment newInstance() {
    Bundle args = new Bundle();
    SubResultsFragment fragment = new SubResultsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_results);
    mResultsAdapter = new ResultsAdapter(getMvpDelegate(), getContext(), mNavigator);
    mRvResults.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvResults.setAdapter(mResultsAdapter);
    mRvResults.setNestedScrollingEnabled(false);
    mRvResults.setFocusable(false);
  }

  @Override public void showLastResults(List<ResultEntity> resultEntities) {
    mResultsAdapter.addListResultEntity(resultEntities);
  }
}
