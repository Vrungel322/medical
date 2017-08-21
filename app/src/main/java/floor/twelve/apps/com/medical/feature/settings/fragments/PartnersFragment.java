package floor.twelve.apps.com.medical.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.PartnerEntity;
import floor.twelve.apps.com.medical.feature.settings.activities.SettingsActivity;
import floor.twelve.apps.com.medical.feature.settings.adapters.PartnersAdapter;
import floor.twelve.apps.com.medical.feature.settings.presenters.PartnersFragmentPresenter;
import floor.twelve.apps.com.medical.feature.settings.views.IPartnersFragment;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

public class PartnersFragment extends BaseFragment implements IPartnersFragment {

  @InjectPresenter PartnersFragmentPresenter mPresenter;

  @BindView(R.id.list_item_view) RecyclerView mRvPartners;
  private PartnersAdapter mPartnersAdapter;

  public PartnersFragment() {
    super(R.layout.fragment_partners);
  }

  public static PartnersFragment newInstance() {
    Bundle args = new Bundle();
    PartnersFragment partnersFragment = new PartnersFragment();
    partnersFragment.setArguments(args);
    return partnersFragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.settings_our_partners);

    mPartnersAdapter = new PartnersAdapter(getMvpDelegate(), getContext());
    mRvPartners.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvPartners.setAdapter(mPartnersAdapter);
    mRvPartners.setNestedScrollingEnabled(false);
    mRvPartners.setFocusable(false);
  }

  @Override public void showPartners(List<PartnerEntity> partnerEntityList) {
    mPartnersAdapter.addPartners(partnerEntityList);
  }

  @Override public void onDestroyView() {
    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.menu_settings);
    super.onDestroyView();
  }
}
