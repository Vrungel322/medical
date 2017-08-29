package floor.twelve.apps.com.medical.data.model;

/**
 * Created by Vrungel on 29.08.2017.
 */

public class SalesEntity {
  private String title;
  private String date;
  private String imgUrl;
  private String timeRemain;
  private String views;
  private String peopleInvolved;

  public SalesEntity(String title, String date, String imgUrl, String timeRemain, String views,
      String peopleInvolved) {
    this.title = title;
    this.date = date;
    this.imgUrl = imgUrl;
    this.timeRemain = timeRemain;
    this.views = views;
    this.peopleInvolved = peopleInvolved;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getTimeRemain() {
    return timeRemain;
  }

  public void setTimeRemain(String timeRemain) {
    this.timeRemain = timeRemain;
  }

  public String getViews() {
    return views;
  }

  public void setViews(String views) {
    this.views = views;
  }

  public String getPeopleInvolved() {
    return peopleInvolved;
  }

  public void setPeopleInvolved(String peopleInvolved) {
    this.peopleInvolved = peopleInvolved;
  }
}
