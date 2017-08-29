package floor.twelve.apps.com.medical.feature.offers.fragmnets;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.feature.offers.presenters.AllSalesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.offers.views.IAllSalesFragmentView;

/**
 * Created by Vrungel on 29.08.2017.
 */
public class AllSalesFragment extends BaseFragment implements IAllSalesFragmentView {
  @InjectPresenter AllSalesFragmentPresenter mAllSalesFragmentyPresenter;

  public static AllSalesFragment newInstance() {
    Bundle args = new Bundle();
    AllSalesFragment fragment = new AllSalesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public AllSalesFragment() {
    super(R.layout.all_sales_fragment);
  }
}
