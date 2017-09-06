package floor.twelve.apps.com.medical.feature.contacts.presenters;

import com.arellomobile.mvp.InjectViewState;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.base.BasePresenter;
import floor.twelve.apps.com.medical.feature.contacts.views.IContactsFragmentView;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class ContactsFragmentPresenter
    extends BasePresenter<IContactsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
