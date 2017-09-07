package floor.twelve.apps.com.medical.feature.booking.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.booking.views.IBookingFragmentView;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class BookingFragmentPresenter extends BasePresenter<IBookingFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}