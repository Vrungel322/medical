package floor.twelve.apps.com.medical.utils;

/**
 * Created by John on 26.01.2017.
 */

public final class RxBusHelper {

  public static class HideFloatingButton {
  }

  public static class ShowFloatingButton {
  }

  public static class ShowAuthDialog {
  }

  public static class MessageConnectException {
  }

  public static class MessageWrongException {
  }

  public static class NoInternetAlertDialog {
  }

  public static class UpdateOffers {
  }

  public static class UpdateLastResultsListEvent {
  }

  public static class SetBookingItemInMenu {
  }

  public static class SetNewsItemInMenu {
  }

  public class UpdateLastBookingListEvent {
  }

  public static class UpdateFavoriteGoodsList {
  }

  public static class ShowResetBtn {
  }

  public static class ReloadCatalogByCategoryLocally {
    public Integer id;
    public String title;

    public ReloadCatalogByCategoryLocally(Integer id, String title) {
      this.id = id;
      this.title = title;
    }
  }

  public static class ReloadCatalogByCategory {
    public Integer id;
    public String title;

    public ReloadCatalogByCategory(Integer id, String title) {
      this.id = id;
      this.title = title;
    }
  }

  public static class UpdateGoodsList {
  }

  public static class UpdateOurWorkList {
  }
}
