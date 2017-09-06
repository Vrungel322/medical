package floor.twelve.apps.com.medical.data.model;

/**
 * Created by alexandersvyatetsky on 6/09/17.
 */

public class ReviewEntity {

  private String id;
  private String userName;
  private String date;
  private String message;
  private String rating;
  private String imageUrl;

  public ReviewEntity(String id, String userName, String date, String message, String rating,
      String imageUrl) {
    this.id = id;
    this.userName = userName;
    this.date = date;
    this.message = message;
    this.rating = rating;
    this.imageUrl = imageUrl;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
