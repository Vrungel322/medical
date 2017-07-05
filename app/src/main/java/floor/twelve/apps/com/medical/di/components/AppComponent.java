package floor.twelve.apps.com.medical.di.components;

import dagger.Component;
import floor.twelve.apps.com.medical.base.BaseActivity;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.di.modules.AppModule;
import floor.twelve.apps.com.medical.di.scopes.AppScope;
import floor.twelve.apps.com.medical.feature.start_point.presenters.StartActivityPresenter;
import floor.twelve.apps.com.medical.utils.jobs.JobsCreator;
import floor.twelve.apps.com.medical.utils.jobs.NotificationJob;

/**
 * Created by Alexandra on 05.07.2017.
 */

@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  //presenters

  void inject(StartActivityPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //job
  void inject(NotificationJob notificationJob);

  void inject(JobsCreator jobsCreator);
}
