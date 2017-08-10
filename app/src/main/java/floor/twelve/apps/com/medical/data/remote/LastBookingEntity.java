package floor.twelve.apps.com.medical.data.remote;

/**
 * Created by Vrungel on 10.08.2017.
 */

public class LastBookingEntity {
  private String serviceType;
  private String serviceName;
  private String serviceTime;
  private String serviceDescription;
  private String address;
  private String doctorsName;
  private String doctorsProf;
  private Integer comments;
  private String doctorsPhoto;
  private Integer status;

  public LastBookingEntity(String serviceType, String serviceName, String serviceTime,
      String serviceDescription, String address, String doctorsName, String doctorsProf,
      Integer comments, String doctorsFace, Integer status) {
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

  public String getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(String serviceTime) {
    this.serviceTime = serviceTime;
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
