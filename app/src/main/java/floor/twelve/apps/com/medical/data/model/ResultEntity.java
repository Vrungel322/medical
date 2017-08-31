package floor.twelve.apps.com.medical.data.model;

/**
 * Created by alexandersvyatetsky on 11/08/17.
 */

public class ResultEntity {
  private String id;
  private String resultName;
  private String resultDescription;
  private String resultDate;

  public ResultEntity(String id, String resultName, String resultDescription, String resultDate) {
    this.id = id;
    this.resultName = resultName;
    this.resultDescription = resultDescription;
    this.resultDate = resultDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getResultName() {
    return resultName;
  }

  public void setResultName(String resultName) {
    this.resultName = resultName;
  }

  public String getResultDescription() {
    return resultDescription;
  }

  public void setResultDescription(String resultDescription) {
    this.resultDescription = resultDescription;
  }

  public String getResultDate() {
    return resultDate;
  }

  public void setResultDate(String resultDate) {
    this.resultDate = resultDate;
  }
}
