package floor.twelve.apps.com.medical.feature.contacts.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.contacts.views.IContactsAboutFragmentView;

/**
 * Created by Alexandra on 21.06.2017.
 */

@InjectViewState public class ContactsAboutFragmentPresenter
    extends BasePresenter<IContactsAboutFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
