package floor.twelve.apps.com.medical.data.model;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

public class PricesCategoryEntity {

  private String id;
  private String name;

  public PricesCategoryEntity(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
