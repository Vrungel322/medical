package floor.twelve.apps.com.medical.feature.offers.fragmnets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.SalesEntity;
import floor.twelve.apps.com.medical.feature.offers.adapters.SalesAdapter;
import floor.twelve.apps.com.medical.feature.offers.presenters.AllSalesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.offers.views.IAllSalesFragmentView;
import java.util.List;

/**
 * Created by Vrungel on 29.08.2017.
 */
public class AllSalesFragment extends BaseFragment implements IAllSalesFragmentView {
  @InjectPresenter AllSalesFragmentPresenter mAllSalesFragmentPresenter;
  @BindView(R.id.rvOffers)RecyclerView mRecyclerViewOffers;
  private SalesAdapter mSalesAdapter;

  public static AllSalesFragment newInstance() {
    Bundle args = new Bundle();
    AllSalesFragment fragment = new AllSalesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public AllSalesFragment() {
    super(R.layout.fragment_all_sales);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mSalesAdapter = new SalesAdapter(getMvpDelegate());
    mRecyclerViewOffers.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewOffers.setAdapter(mSalesAdapter);
  }

  @Override public void addList(List<SalesEntity> salesEntities) {
    mSalesAdapter.addListSalesEntity(salesEntities);
  }
}
