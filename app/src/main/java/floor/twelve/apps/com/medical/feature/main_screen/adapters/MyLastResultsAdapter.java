package floor.twelve.apps.com.medical.feature.results.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.base.Navigator;
import floor.twelve.apps.com.medical.data.model.ResultEntity;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.MyLastResultsAdapterPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.views.IMyLastResultsAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

public class MyLastResultsAdapter
    extends MvpBaseRecyclerAdapter<MyLastResultsAdapter.ResultsViewHolder>
    implements IMyLastResultsAdapter {

  @InjectPresenter MyLastResultsAdapterPresenter mPresenter;

  private Context mContext;
  private Navigator mNavigator;
  private ArrayList<ResultEntity> mResultEntities = new ArrayList<>();

  public MyLastResultsAdapter(MvpDelegate<?> parentDelegate, Context context, Navigator navigator) {
    super(parentDelegate, "MyLastResultsAdapterPresenter");
    this.mContext = context;
    this.mNavigator = navigator;
  }

  public void addListResultEntity(List<ResultEntity> resultEntities) {
    mResultEntities.clear();
    mResultEntities.addAll(resultEntities);
    notifyDataSetChanged();
  }

  @Override public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ResultsViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false));
  }

  @Override public void onBindViewHolder(ResultsViewHolder holder, int position) {
    holder.tvResultName.setText(mResultEntities.get(position).getResultName());
    holder.tvResultDescription.setText(mResultEntities.get(position).getResultDescription());
    holder.tvResultDate.setText(mResultEntities.get(position).getResultDate());
  }

  @Override public int getItemCount() {
    return mResultEntities.size();
  }

  public class ResultsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvResultName) TextView tvResultName;
    @BindView(R.id.tvResultDescription) TextView tvResultDescription;
    @BindView(R.id.tvResultDate) TextView tvResultDate;
    @BindView(R.id.ivNotifications) AppCompatImageView ivNotifications;

    public ResultsViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
