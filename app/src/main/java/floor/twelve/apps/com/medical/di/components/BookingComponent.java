package floor.twelve.apps.com.medical.di.components;

import dagger.Subcomponent;
import floor.twelve.apps.com.medical.di.modules.BookingModule;
import floor.twelve.apps.com.medical.di.scopes.BookingScope;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.BookingDetailMasterFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.ChooseMasterContactFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.ChooseMasterMasterFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.ChooseMasterServiceFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_master.presenters.ChooseMasterTimeFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters.BookingDetailServiceFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters.ChooseServiceContactFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters.ChooseServiceMasterFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters.ChooseServiceServiceFragmentPresenter;
import floor.twelve.apps.com.medical.feature.booking.mode.booking_service.presenters.ChooseServiceTimeFragmentPresenter;

/**
 * Created by Vrungel on 24.03.2017.
 */

@BookingScope @Subcomponent(modules = BookingModule.class) public interface BookingComponent {

  void inject(ChooseServiceServiceFragmentPresenter presenter);

  void inject(ChooseServiceTimeFragmentPresenter presenter);

  void inject(ChooseServiceMasterFragmentPresenter presenter);

  void inject(ChooseServiceContactFragmentPresenter presenter);

  void inject(ChooseMasterMasterFragmentPresenter presenter);

  void inject(ChooseMasterServiceFragmentPresenter presenter);

  void inject(ChooseMasterTimeFragmentPresenter presenter);

  void inject(ChooseMasterContactFragmentPresenter presenter);

  void inject(BookingDetailMasterFragmentPresenter presenter);

  void inject(BookingDetailServiceFragmentPresenter presenter);
}
