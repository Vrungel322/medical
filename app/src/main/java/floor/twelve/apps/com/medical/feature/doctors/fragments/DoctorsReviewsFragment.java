package floor.twelve.apps.com.medical.feature.doctors.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.ReviewEntity;
import floor.twelve.apps.com.medical.feature.doctors.adapters.DoctorsReviewsAdapter;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorsReviewsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsReviewsFragmentView;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class DoctorsReviewsFragment extends BaseFragment implements IDoctorsReviewsFragmentView {

  @InjectPresenter DoctorsReviewsFragmentPresenter mPresenter;
  @BindView(R.id.rvDoctorsReviews) RecyclerView rvDoctorsReviews;
  private DoctorsReviewsAdapter mAdapter;

  public DoctorsReviewsFragment() {
    super(R.layout.fragment_doctors_reviews);
  }

  public static DoctorsReviewsFragment newInstance() {
    Bundle args = new Bundle();
    DoctorsReviewsFragment fragment = new DoctorsReviewsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mAdapter = new DoctorsReviewsAdapter(getMvpDelegate());

    rvDoctorsReviews.setLayoutManager(new LinearLayoutManager(getContext()));
    rvDoctorsReviews.setHasFixedSize(true);
    rvDoctorsReviews.setAdapter(mAdapter);
  }

  @Override public void showReview(List<ReviewEntity> list) {
    mAdapter.addListReviewEntities(list);
  }

  @OnClick(R.id.fab_feedback) public void giveFeedback() {
    FeedbackDialog dialog = new FeedbackDialog();
    dialog.show(getActivity().getFragmentManager(), "");
  }
}
