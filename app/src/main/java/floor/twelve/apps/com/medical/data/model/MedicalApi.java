package floor.twelve.apps.com.medical.data.model;

import floor.twelve.apps.com.medical.data.model.goods.GoodsEntity;
import floor.twelve.apps.com.medical.data.model.goods.category.GoodsCategoryEntity;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alexandra on 05.07.2017.
 */

public interface MedicalApi {

  @GET("api/v1/pages") Observable<Response<List<NewsEntity>>> fetchAllNews(
      @Header("lng") String language);

  @GET("api/v1/users/favorite/products") Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods(
      @Header("lng") String language, @Header("authorization") String token);

  @GET("api/v1/products") Observable<Response<List<GoodsEntity>>> fetchGoodsByCatalogId(
      @Header("lng") String language, @Query("category") Integer id);

  @GET("api/v1/products") Observable<Response<List<GoodsEntity>>> fetchAllProducts(
      @Header("lng") String language, @Header("authorization") String token);

  @GET("api/v1/products/categories")
  Observable<Response<List<GoodsCategoryEntity>>> fetchCategories(@Header("lng") String language);

  @POST("api/v1/users/favorite/products") @FormUrlEncoded
  Observable<Response<Void>> addToFavoriteGoods(@Header("lng") String language,
      @Field("product_id") int goodsId, @Header("authorization") String token);

  @DELETE("api/v1/users/favorite/products/{product_id}")
  Observable<Response<Void>> removeFromFavoriteGoods(@Header("lng") String language,
      @Path("product_id") int goodsId, @Header("authorization") String token);
}
