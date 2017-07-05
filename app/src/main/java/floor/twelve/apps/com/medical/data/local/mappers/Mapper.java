package floor.twelve.apps.com.medical.data.local.mappers;

/**
 * Created by Alexandra on 05.07.2017.
 */

public interface Mapper<A, B> {
  B transform(A obj) throws RuntimeException;
}

