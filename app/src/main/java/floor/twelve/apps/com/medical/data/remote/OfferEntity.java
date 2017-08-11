package floor.twelve.apps.com.medical.data.remote;

/**
 * Created by Vrungel on 11.08.2017.
 */

public class OfferEntity {
  private String logoUri;
  private String offerName;
  private String offerDescription;
  private String numPeoplePass;
  private String numWatches;
  private String numDays;

  public OfferEntity(String logoUri, String offerName, String offerDescription,
      String numPeoplePass, String numWatches, String numDays) {
    this.logoUri = logoUri;
    this.offerName = offerName;
    this.offerDescription = offerDescription;
    this.numPeoplePass = numPeoplePass;
    this.numWatches = numWatches;
    this.numDays = numDays;
  }

  public String getLogoUri() {
    return logoUri;
  }

  public void setLogoUri(String logoUri) {
    this.logoUri = logoUri;
  }

  public String getOfferName() {
    return offerName;
  }

  public void setOfferName(String offerName) {
    this.offerName = offerName;
  }

  public String getOfferDescription() {
    return offerDescription;
  }

  public void setOfferDescription(String offerDescription) {
    this.offerDescription = offerDescription;
  }

  public String getNumPeoplePass() {
    return numPeoplePass;
  }

  public void setNumPeoplePass(String numPeoplePass) {
    this.numPeoplePass = numPeoplePass;
  }

  public String getNumWatches() {
    return numWatches;
  }

  public void setNumWatches(String numWatches) {
    this.numWatches = numWatches;
  }

  public String getNumDays() {
    return numDays;
  }

  public void setNumDays(String numDays) {
    this.numDays = numDays;
  }
}
