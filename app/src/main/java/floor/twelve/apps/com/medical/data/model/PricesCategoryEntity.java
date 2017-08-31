package floor.twelve.apps.com.medical.data.model;

import java.util.ArrayList;

/**
 * Created by alexandersvyatetsky on 29/08/17.
 */

public class PricesCategoryEntity {

  private String id;
  private String name;
  private ArrayList<PriceEntity> priceEntities;

  public PricesCategoryEntity(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public PricesCategoryEntity(String id, String name, ArrayList<PriceEntity> priceEntities) {
    this.id = id;
    this.name = name;
    this.priceEntities = priceEntities;
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

  public ArrayList<PriceEntity> getPriceEntities() {
    return priceEntities;
  }

  public void setPriceEntities(ArrayList<PriceEntity> priceEntities) {
    this.priceEntities = priceEntities;
  }
}
