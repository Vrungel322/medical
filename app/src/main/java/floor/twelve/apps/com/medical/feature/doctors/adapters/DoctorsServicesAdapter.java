package floor.twelve.apps.com.medical.feature.doctors.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.MvpBaseRecyclerAdapter;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import floor.twelve.apps.com.medical.feature.doctors.presenters.DoctorsServicesAdapterPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IDoctorsServicesAdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class DoctorsServicesAdapter
    extends MvpBaseRecyclerAdapter<DoctorsServicesAdapter.DoctorsServiceViewHolder>
    implements IDoctorsServicesAdapterView {

  @InjectPresenter DoctorsServicesAdapterPresenter mPresenter;

  private Context mContext;
  private ArrayList<ServiceEntity> serviceEntities = new ArrayList<>();

  public DoctorsServicesAdapter(MvpDelegate<?> parentDelegate, Context context) {
    super(parentDelegate, "DoctorsServicesAdapter");
    this.mContext = context;
  }

  public void addListServiceEntities(List<ServiceEntity> list) {
    serviceEntities.clear();
    serviceEntities.addAll(list);
    notifyDataSetChanged();
  }

  @Override public DoctorsServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DoctorsServiceViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_doctors_service, parent, false));
  }

  @Override public void onBindViewHolder(DoctorsServiceViewHolder holder, int position) {
    holder.tvServiceName.setText(serviceEntities.get(position).getTitle());
    holder.tvServiceDescription.setText(serviceEntities.get(position).getDescription());
    // TODO: 6/09/17 set address
    holder.tvServicePrice.setText(
        mContext.getString(R.string.booking_price, serviceEntities.get(position).getPrice()));
  }

  @Override public int getItemCount() {
    return serviceEntities.size();
  }

  class DoctorsServiceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvServiceName) TextView tvServiceName;
    @BindView(R.id.cbArrow) AppCompatCheckBox cbArrow;
    @BindView(R.id.tvServiceDescription) TextView tvServiceDescription;
    @BindView(R.id.tvAddress) TextView tvAddress;
    @BindView(R.id.tvServicePrice) TextView tvServicePrice;
    @BindView(R.id.clDoctorInfo) ConstraintLayout clDoctorInfo;

    public DoctorsServiceViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @OnClick({ R.id.clParent, R.id.cbArrow }) public void onCardClicked(View view) {
      Animation rotation = null;
      if (cbArrow.isChecked()) {
        cbArrow.setChecked(false);
        clDoctorInfo.setVisibility(View.GONE);
        rotation = AnimationUtils.loadAnimation(mContext, R.anim.back_rotate);
      } else {
        cbArrow.setChecked(true);
        clDoctorInfo.setVisibility(View.VISIBLE);
        rotation = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
      }
      cbArrow.startAnimation(rotation);
    }
  }
}
