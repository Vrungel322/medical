package floor.twelve.apps.com.medical.data.remote;

import floor.twelve.apps.com.medical.data.model.MedicalApi;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.data.model.OurWorkEntity;
import floor.twelve.apps.com.medical.data.model.PhotoWorksEntity;
import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import floor.twelve.apps.com.medical.data.model.goods.category.GoodsCategoryEntity;
import java.util.List;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class RestApi {
  private final MedicalApi api;

  public RestApi(MedicalApi api) {
    this.api = api;
  }

  public Observable<Response<List<NewsEntity>>> fetchAllNews(String language) {
    return api.fetchAllNews(language);
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods(String language, String token) {
    return api.fetchFavoriteGoods(language, token);
  }

  public Observable<Response<List<GoodsEntity>>> fetchGoodsByCatalogId(String language,
      Integer id) {
    return api.fetchGoodsByCatalogId(language, id);
  }

  public Observable<Response<List<GoodsEntity>>> fetchAllProducts(String language, String token) {
    return api.fetchAllProducts(language, token);
  }

  public Observable<Response<List<GoodsCategoryEntity>>> fetchCategories(String language) {
    return api.fetchCategories(language);
  }

  public Observable<Response<Void>> addToFavoriteGoods(String language, int goodsId, String token) {
    return api.addToFavoriteGoods(language, goodsId, token);
  }

  public Observable<Response<Void>> removeFromFavoriteGoods(String language, int goodsId,
      String token) {
    return api.removeFromFavoriteGoods(language, goodsId, token);
  }

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorks(String language) {
    return api.fetchListOfWorks(language);
  }

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorksAuth(String language,
      String token) {
    return api.fetchListOfWorksAuth(language, token);
  }

  public Observable<Response<Void>> addToFavoritePhoto(String language, int photoId, String token) {
    return api.addToFavoritePhoto(language, photoId, token);
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(String language, int photoId,
      String token) {
    return api.removeFromFavoritePhoto(language, photoId, token);
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos(String language,
      String token) {
    return api.fetchFavoritePhotos(language, token);
  }
}
