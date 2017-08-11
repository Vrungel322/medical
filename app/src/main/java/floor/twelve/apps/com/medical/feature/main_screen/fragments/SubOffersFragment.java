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
import floor.twelve.apps.com.medical.data.remote.OfferEntity;
import floor.twelve.apps.com.medical.feature.main_screen.adapters.SubOffersAdapter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubOffersFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.ISubOffersFragmentView;
import java.util.List;

/**
 * Created by Vrungel on 11.08.2017.
 */

public class SubOffersFragment extends BaseFragment implements ISubOffersFragmentView {
  @InjectPresenter SubOffersFragmentPresenter mSubOffersFragmentPresenter;
  @BindView(R.id.rvOffers) RecyclerView mRecyclerViewOffers;
  private SubOffersAdapter mSubOffersAdapter;

  public static SubOffersFragment newInstance() {
    Bundle args = new Bundle();
    SubOffersFragment fragment = new SubOffersFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public SubOffersFragment() {
    super(R.layout.fragment_sub_offers);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mSubOffersAdapter = new SubOffersAdapter(getMvpDelegate(), getContext(), mNavigator);
    mRecyclerViewOffers.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mRecyclerViewOffers.setAdapter(mSubOffersAdapter);
    mRecyclerViewOffers.setNestedScrollingEnabled(false);
    mRecyclerViewOffers.setFocusable(false);
  }

  @Override public void showOffers(List<OfferEntity> offerEntities) {
    mSubOffersAdapter.addOffersEntity(offerEntities);
  }
}
