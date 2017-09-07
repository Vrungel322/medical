package floor.twelve.apps.com.medical.di.modules;

import dagger.Module;
import dagger.Provides;
import floor.twelve.apps.com.medical.data.local.mappers.BookingToBookingServerEntityMapper;
import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.di.scopes.BookingScope;

/**
 * Created by Vrungel on 24.03.2017.
 */

@Module public class BookingModule {
  @Provides @BookingScope BookingEntity provideBookingEntity() {
    return new BookingEntity("", "", "", "", "", "", "", "", "");
  }

  @Provides @BookingScope BookingToBookingServerEntityMapper provideMApper() {
    return new BookingToBookingServerEntityMapper();
  }
}
