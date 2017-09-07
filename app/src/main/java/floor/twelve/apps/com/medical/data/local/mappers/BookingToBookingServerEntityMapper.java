package floor.twelve.apps.com.medical.data.local.mappers;

import floor.twelve.apps.com.medical.data.model.BookingEntity;
import floor.twelve.apps.com.medical.data.model.BookingServerEntity;

/**
 * Created by vrungel on 19.04.17.
 */

public class BookingToBookingServerEntityMapper
    implements Mapper<BookingEntity, BookingServerEntity> {

  @Override public BookingServerEntity transform(BookingEntity obj) throws RuntimeException {
    return new BookingServerEntity(Integer.parseInt(obj.getMasterId()),
        Integer.parseInt(obj.getServiceId()), Integer.parseInt(obj.getDateId()), obj.getUserName(),
        obj.getUserPhone());
  }
}
