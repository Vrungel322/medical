package floor.twelve.apps.com.medical.data.model;

/**
 * Created by Vrungel on 31.08.2017.
 */

public class PhotoGalleryEntity {
  private String title;
  private String imgUrl;
  private String imgCount;

  public PhotoGalleryEntity(String title, String imgUrl, String imgCount) {
    this.title = title;
    this.imgUrl = imgUrl;
    this.imgCount = imgCount;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getImgCount() {
    return imgCount;
  }

  public void setImgCount(String imgCount) {
    this.imgCount = imgCount;
  }
}
