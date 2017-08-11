package floor.twelve.apps.com.medical.data.model;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

public class ResultEntity {
  private String mResultId;
  private String mResultName;
  private String mResultDescription;
  private String mResultDate;

  public ResultEntity(String mResultId, String mResultName, String mResultDescription,
      String mResultDate) {
    this.mResultId = mResultId;
    this.mResultName = mResultName;
    this.mResultDescription = mResultDescription;
    this.mResultDate = mResultDate;
  }

  public String getmResultId() {
    return mResultId;
  }

  public void setmResultId(String mResultId) {
    this.mResultId = mResultId;
  }

  public String getmResultName() {
    return mResultName;
  }

  public void setmResultName(String mResultName) {
    this.mResultName = mResultName;
  }

  public String getmResultDescription() {
    return mResultDescription;
  }

  public void setmResultDescription(String mResultDescription) {
    this.mResultDescription = mResultDescription;
  }

  public String getmResultDate() {
    return mResultDate;
  }

  public void setmResultDate(String mResultDate) {
    this.mResultDate = mResultDate;
  }
}
