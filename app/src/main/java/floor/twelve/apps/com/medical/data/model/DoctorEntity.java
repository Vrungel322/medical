package floor.twelve.apps.com.medical.data.model;

/**
 * Created by alexandersvyatetsky on 5/09/17.
 */

public class DoctorEntity {

  private String id;
  private String imageUrl;
  private String name;
  private String speciality;
  private String rank;
  private int rating;
  private int reviewsCount;

  public DoctorEntity(String id, String imageUrl, String name, String speciality, String rank,
      int rating, int reviewsCount) {
    this.id = id;
    this.imageUrl = imageUrl;
    this.name = name;
    this.speciality = speciality;
    this.rank = rank;
    this.rating = rating;
    this.reviewsCount = reviewsCount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public int getReviewsCount() {
    return reviewsCount;
  }

  public void setReviewsCount(int reviewsCount) {
    this.reviewsCount = reviewsCount;
  }
}
