package floor.twelve.apps.com.medical.data.remote;

import floor.twelve.apps.com.medical.data.model.MedicalApi;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
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
}
