package floor.twelve.apps.com.medical.feature.our_works.fragments;

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
import floor.twelve.apps.com.medical.data.model.OurWorkEntity;
import floor.twelve.apps.com.medical.feature.our_works.adapters.OurWorkAdapter;
import floor.twelve.apps.com.medical.feature.our_works.presenters.OurWorkFragmentPresenter;
import floor.twelve.apps.com.medical.feature.our_works.views.IOurWorkFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import floor.twelve.apps.com.medical.utils.ItemClickSupport;
import floor.twelve.apps.com.medical.utils.ThemeUtils;
import floor.twelve.apps.com.medical.utils.ViewUtil;
import java.util.List;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class OurWorkFragment extends BaseFragment implements IOurWorkFragmentView {

  @InjectPresenter OurWorkFragmentPresenter mOurWorkFragmentPresenter;

  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.rvOurWorks) RecyclerView mRecyclerViewOurWorks;
  @BindView(R.id.textViewOurWorkIsEmpty) TextView mTextViewOurWorkIsEmpty;

  private OurWorkAdapter mOurWorkAdapter;

  public OurWorkFragment() {
    super(R.layout.fragment_our_work);
  }

  public static OurWorkFragment newInstance() {
    Bundle args = new Bundle();
    OurWorkFragment fragment = new OurWorkFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_our_work);

    mOurWorkAdapter = new OurWorkAdapter();
    mRecyclerViewOurWorks.setLayoutManager(
        new GridLayoutManager(getContext(), ViewUtil.getRotation(getContext())));
    mRecyclerViewOurWorks.setAdapter(mOurWorkAdapter);

    ItemClickSupport.addTo(mRecyclerViewOurWorks)
        .setOnItemClickListener((recyclerView, position, v) -> {
          if (position == 0 && !mAuthorizationManager.isAuthorized()) {
            mOurWorkFragmentPresenter.showAlertDialog();
          } else if (mOurWorkAdapter.getEntity(position).getImageCount() != 0) {
            mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
                WorkDetailsFragment.newInstance(mOurWorkAdapter.getEntity(position)));
          } else {
            showToastMessage(R.string.list_is_empty);
          }
        });

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeResources(value.resourceId);
    mSwipeRefreshLayout.setOnRefreshListener(() -> mOurWorkFragmentPresenter.fetchWorksCondition());
  }

  @Override public void addListOfWorks(List<OurWorkEntity> ourWorkEntities) {
    mOurWorkAdapter.addListWorkEntities(ourWorkEntities);
    if (!ourWorkEntities.isEmpty()) {
      mTextViewOurWorkIsEmpty.setVisibility(View.GONE);
    } else {
      mTextViewOurWorkIsEmpty.setVisibility(View.VISIBLE);
    }
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

  @Override public void showServerErrorMsg() {
    showLongAlertMessage(getString(R.string.dialog_error_title), getString(R.string.server_error));
  }
}
