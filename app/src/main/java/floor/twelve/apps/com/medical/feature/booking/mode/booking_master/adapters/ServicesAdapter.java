package floor.twelve.apps.com.medical.feature.booking.mode.booking_master.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.data.model.ServiceEntity;
import floor.twelve.apps.com.medical.utils.DialogFactory;
import java.util.ArrayList;
import java.util.List;

import static floor.twelve.apps.com.medical.utils.Constants.Other.SERVER_ANSWER_EMPTY_STRING;

/**
 * Created by Vrungel on 29.03.2017.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {
  private List<ServiceEntity> mServiceEntities = new ArrayList<>();

  private int selectedItem = -1;
  private Context mContext;

  public ServicesAdapter(Context context) {
    this.mContext = context;
  }

  public void setServiceEntity(List<ServiceEntity> serviceEntities) {
    mServiceEntities.clear();
    mServiceEntities.addAll(serviceEntities);
    notifyDataSetChanged();
  }

  @Override public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_booking_service, parent, false);
    return new ServicesAdapter.ServiceViewHolder(v);
  }

  @Override public void onBindViewHolder(ServiceViewHolder holder, int position) {
    if (this.selectedItem == position) {
      TypedValue value = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
      holder.mConstraintLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mConstraintLayoutParent.getContext(), value.resourceId));
      holder.mConstraintLayoutParent.getBackground().setAlpha(30);
    } else {
      holder.mConstraintLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mConstraintLayoutParent.getContext(), R.color.colorWhite));
    }
    Glide.with(mContext)
        .load(mServiceEntities.get(position).getImage())
        .centerCrop()
        .placeholder(
            AppCompatResources.getDrawable(mContext, R.drawable.ic_service_placeholder_24dp))
        .error(AppCompatResources.getDrawable(mContext, R.drawable.ic_service_placeholder_24dp))
        .dontAnimate()
        .into(holder.mImageViewServiceImg);

    holder.mTextViewServiceName.setText(mServiceEntities.get(position).getTitle());
    holder.mTextViewAboutService.setText(mContext.getText(R.string.detail));
    holder.mTextViewAboutService.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    holder.mTextViewAboutService.setOnClickListener(view -> {

      DialogFactory.createAlertDialogBuilder(mContext,mServiceEntities.get(position).getTitle())
          .setMessage(
              mServiceEntities.get(position).getDescription())
          .setNegativeButton(R.string.dialog_cancel,
              (dialogInterface, i) -> dialogInterface.cancel())
          .create()
          .show();
    });
    holder.mTextViewServicePrice.setText(mServiceEntities.get(position).getPrice());
    holder.mTextViewSServiceDuration.setText(mServiceEntities.get(position).getTime());

    if (!mServiceEntities.get(position).getBonusPrice().equals(SERVER_ANSWER_EMPTY_STRING)) {
      holder.mTextViewPriceBonus.setText(mServiceEntities.get(position).getBonusPrice());
    } else {
      holder.mTextViewPriceBonus.setVisibility(View.GONE);
      holder.mImageViewPriceBonus.setVisibility(View.GONE);
    }

    /* check if service is new */
    if (mServiceEntities.get(position).isNew()) {
      holder.mImageViewServiceBadgeNew.setImageResource(R.drawable.ic_badge_new_32dp);
    } else {
      holder.mImageViewServiceBadgeNew.setVisibility(View.GONE);
    }
        /* check if for sale */
    if (mServiceEntities.get(position).isForSale()) {
      holder.mImageViewServiceBadgeSale.setImageResource(R.drawable.ic_badge_sale_32dp);
    } else {
      holder.mImageViewServiceBadgeSale.setVisibility(View.GONE);
    }

    if (this.selectedItem == position) {
      TypedValue value = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
      holder.mConstraintLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.itemView.getContext(), value.resourceId));
      holder.mConstraintLayoutParent.getBackground().setAlpha(30);
    } else {
      holder.mConstraintLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.itemView.getContext(), R.color.colorWhite));
    }
  }

  @Override public int getItemCount() {
    return mServiceEntities.size();
  }

  public void setSelectedItem(int position) {
    this.selectedItem = position;
    this.notifyDataSetChanged();
  }

  public void setSelectedItem(String serviceId) {
    for (int i = 0; i < mServiceEntities.size(); i++) {
      if (String.valueOf(mServiceEntities.get(i).getServiceId()).equals(serviceId)) {
        setSelectedItem(i);
      }
    }
  }

  static class ServiceViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.clParent) ConstraintLayout mConstraintLayoutParent;
    @BindView(R.id.ivServiceImg) CircleImageView mImageViewServiceImg;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvAboutService) TextView mTextViewAboutService;
    @BindView(R.id.tvServicePrice) TextView mTextViewServicePrice;
    @BindView(R.id.tvServiceDuration) TextView mTextViewSServiceDuration;
    @BindView(R.id.ivServiceBadgeNew) ImageView mImageViewServiceBadgeNew;
    @BindView(R.id.ivServiceBadgeSale) ImageView mImageViewServiceBadgeSale;
    @BindView(R.id.tvPriceBonus) TextView mTextViewPriceBonus;
    @BindView(R.id.ivBonusPrice) ImageView mImageViewPriceBonus;

    ServiceViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
