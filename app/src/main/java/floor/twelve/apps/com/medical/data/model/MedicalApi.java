package floor.twelve.apps.com.medical.data.model;

import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by Alexandra on 05.07.2017.
 */

public interface MedicalApi {

  @GET("api/v1/pages") Observable<Response<List<NewsEntity>>> fetchAllNews(
      @Header("lng") String language);

}
