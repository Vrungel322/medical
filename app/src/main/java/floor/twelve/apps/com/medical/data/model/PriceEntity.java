package floor.twelve.apps.com.medical.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexandersvyatetsky on 31/08/17.
 */

public class PriceEntity implements Parcelable {

  String id;
  String serviceName;
  String price;

  public PriceEntity(String id, String serviceName, String price) {
    this.id = id;
    this.serviceName = serviceName;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.serviceName);
    dest.writeString(this.price);
  }

  protected PriceEntity(Parcel in) {
    this.id = in.readString();
    this.serviceName = in.readString();
    this.price = in.readString();
  }

  public static final Parcelable.Creator<PriceEntity> CREATOR =
      new Parcelable.Creator<PriceEntity>() {
        @Override public PriceEntity createFromParcel(Parcel source) {
          return new PriceEntity(source);
        }

        @Override public PriceEntity[] newArray(int size) {
          return new PriceEntity[size];
        }
      };
}
