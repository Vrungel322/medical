package floor.twelve.apps.com.medical.data.remote;

import com.google.gson.annotations.SerializedName;
import floor.twelve.apps.com.medical.data.model.StatusHistory;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vrungel on 10.08.2017.
 */

public class LastBookingEntity {
  private String serviceType;
  private String serviceName;
  private String serviceDescription;
  private String address;
  private String doctorsName;
  private String doctorsProf;
  private Integer comments;
  private String doctorsPhoto;
  @SerializedName("service_bonus_price") private String serviceBonusPrice;
  @SerializedName("service_bonus_add") private String serviceBonusAdd;
  @SerializedName("service_price") private String servicePrice;
  @SerializedName("service_new_price") private String serviceNewPrice;
  @SerializedName("service_duration") private String serviceDuration;
  @PrimaryKey @SerializedName("id") private Integer id;
  @SerializedName("phone") private String userPhone;
  @SerializedName("name") private String userName;
  @SerializedName("status") private String status;
  @SerializedName("status_history") private RealmList<StatusHistory> statusHistory = null;
  @SerializedName("master") private String masterName;
  @SerializedName("master_description") private String masterDescription;
  @SerializedName("master_photo") private String masterPhoto;
  @SerializedName("master_id") private Integer masterId;
  @SerializedName("schedule") private Integer serviceTime;
  @SerializedName("schedule_id") private Integer scheduleId;
  @SerializedName("service_id") private Integer serviceId;

  public LastBookingEntity(String serviceType, String serviceName, Integer serviceTime,
      String serviceDescription, String address, String doctorsName, String doctorsProf,
      Integer comments, String doctorsFace, String status) {
    this.serviceType = serviceType;
    this.serviceName = serviceName;
    this.serviceTime = serviceTime;
    this.serviceDescription = serviceDescription;
    this.address = address;
    this.doctorsName = doctorsName;
    this.doctorsProf = doctorsProf;
    this.comments = comments;
    this.doctorsPhoto = doctorsFace;
    this.status = status;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceDescription() {
    return serviceDescription;
  }

  public void setServiceDescription(String serviceDescription) {
    this.serviceDescription = serviceDescription;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDoctorsName() {
    return doctorsName;
  }

  public void setDoctorsName(String doctorsName) {
    this.doctorsName = doctorsName;
  }

  public String getDoctorsProf() {
    return doctorsProf;
  }

  public void setDoctorsProf(String doctorsProf) {
    this.doctorsProf = doctorsProf;
  }

  public Integer getComments() {
    return comments;
  }

  public void setComments(Integer comments) {
    this.comments = comments;
  }

  public String getDoctorsPhoto() {
    return doctorsPhoto;
  }

  public void setDoctorsPhoto(String doctorsPhoto) {
    this.doctorsPhoto = doctorsPhoto;
  }

  public String getServiceBonusPrice() {
    return serviceBonusPrice;
  }

  public void setServiceBonusPrice(String serviceBonusPrice) {
    this.serviceBonusPrice = serviceBonusPrice;
  }

  public String getServiceBonusAdd() {
    return serviceBonusAdd;
  }

  public void setServiceBonusAdd(String serviceBonusAdd) {
    this.serviceBonusAdd = serviceBonusAdd;
  }

  public String getServicePrice() {
    return servicePrice;
  }

  public void setServicePrice(String servicePrice) {
    this.servicePrice = servicePrice;
  }

  public String getServiceNewPrice() {
    return serviceNewPrice;
  }

  public void setServiceNewPrice(String serviceNewPrice) {
    this.serviceNewPrice = serviceNewPrice;
  }

  public String getServiceDuration() {
    return serviceDuration;
  }

  public void setServiceDuration(String serviceDuration) {
    this.serviceDuration = serviceDuration;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public RealmList<StatusHistory> getStatusHistory() {
    return statusHistory;
  }

  public void setStatusHistory(RealmList<StatusHistory> statusHistory) {
    this.statusHistory = statusHistory;
  }

  public String getMasterName() {
    return masterName;
  }

  public void setMasterName(String masterName) {
    this.masterName = masterName;
  }

  public String getMasterDescription() {
    return masterDescription;
  }

  public void setMasterDescription(String masterDescription) {
    this.masterDescription = masterDescription;
  }

  public String getMasterPhoto() {
    return masterPhoto;
  }

  public void setMasterPhoto(String masterPhoto) {
    this.masterPhoto = masterPhoto;
  }

  public Integer getMasterId() {
    return masterId;
  }

  public void setMasterId(Integer masterId) {
    this.masterId = masterId;
  }

  public Integer getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(Integer serviceTime) {
    this.serviceTime = serviceTime;
  }

  public Integer getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }
}
