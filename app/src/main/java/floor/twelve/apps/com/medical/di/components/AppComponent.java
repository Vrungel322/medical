package floor.twelve.apps.com.medical.di.components;

import dagger.Component;
import floor.twelve.apps.com.medical.base.BaseActivity;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.di.modules.AppModule;
import floor.twelve.apps.com.medical.di.scopes.AppScope;
import floor.twelve.apps.com.medical.feature.photo_gallery.presenters.PhotoGalleryFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.MainFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.MyLastBookingAdapterPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.MyLastResultsAdapterPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubBookingFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubOffersAdapterPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubOffersFragmentPresenter;
import floor.twelve.apps.com.medical.feature.main_screen.presenters.SubResultsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.my_booking.MyBookFragmentPresenter;
import floor.twelve.apps.com.medical.feature.news.presenters.AllNewsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.news.presenters.DetailNewsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.news.presenters.ListNewsDetailPresenter;
import floor.twelve.apps.com.medical.feature.offers.presenters.AllSalesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesAdapterPresenter;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesCategoriesAdapterPresenter;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesCategoriesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.prices.presenters.PricesFragmentPresenter;
import floor.twelve.apps.com.medical.feature.results.presenters.ResultsAdapterPresenter;
import floor.twelve.apps.com.medical.feature.results.presenters.ResultsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.settings.fragments.AboutApplicationDialog;
import floor.twelve.apps.com.medical.feature.settings.fragments.ThemeDialogFragment;
import floor.twelve.apps.com.medical.feature.settings.presenters.AboutApplicationDialogPresenter;
import floor.twelve.apps.com.medical.feature.settings.presenters.NotificationSettingsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.settings.presenters.PartnersAdapterPresenter;
import floor.twelve.apps.com.medical.feature.settings.presenters.PartnersFragmentPresenter;
import floor.twelve.apps.com.medical.feature.settings.presenters.ReportProblemFragmentPresenter;
import floor.twelve.apps.com.medical.feature.settings.presenters.SettingsActivityPresenter;
import floor.twelve.apps.com.medical.feature.settings.presenters.SettingsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.settings.presenters.ThemeDialogFragmentPresenter;
import floor.twelve.apps.com.medical.feature.start_point.presenters.StartActivityPresenter;
import floor.twelve.apps.com.medical.utils.NetworkBroadcastReceiver;
import floor.twelve.apps.com.medical.utils.custom_views.FabNestedScroll;
import floor.twelve.apps.com.medical.utils.custom_views.FabRecyclerView;
import floor.twelve.apps.com.medical.utils.jobs.JobsCreator;
import floor.twelve.apps.com.medical.utils.jobs.NotificationJob;

/**
 * Created by Alexandra on 05.07.2017.
 */

@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  //presenters

  void inject(StartActivityPresenter presenter);

  void inject(MainFragmentPresenter presenter);

  void inject(SubBookingFragmentPresenter presenter);

  void inject(MyLastBookingAdapterPresenter presenter);

  void inject(SubOffersFragmentPresenter presenter);

  void inject(SubOffersAdapterPresenter presenter);

  void inject(MyBookFragmentPresenter presenter);

  void inject(AllSalesFragmentPresenter presenter);

  void inject(PhotoGalleryFragmentPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  void inject(ThemeDialogFragment themeDialogFragment);

  void inject(AboutApplicationDialog aboutApplicationDialog);

  void inject(SubResultsFragmentPresenter subResultsFragmentPresenter);

  //presenters
  void inject(SettingsActivityPresenter settingsActivityPresenter);

  void inject(SettingsFragmentPresenter settingsFragmentPresenter);

  void inject(NotificationSettingsFragmentPresenter notificationSettingsFragmentPresenter);

  void inject(ReportProblemFragmentPresenter reportProblemFragmentPresenter);

  void inject(ThemeDialogFragmentPresenter themeDialogFragmentPresenter);

  void inject(AboutApplicationDialogPresenter aboutApplicationDialogPresenter);

  void inject(ResultsFragmentPresenter resultsFragmentPresenter);

  void inject(ResultsAdapterPresenter resultsAdapterPresenter);

  void inject(MyLastResultsAdapterPresenter myLastResultsAdapterPresenter);

  void inject(PartnersAdapterPresenter partnersAdapterPresenter);

  void inject(PartnersFragmentPresenter partnersFragmentPresenter);

  void inject(AllNewsFragmentPresenter allNewsFragmentPresenter);

  void inject(DetailNewsFragmentPresenter detailNewsFragmentPresenter);

  void inject(ListNewsDetailPresenter listNewsDetailPresenter);

  void inject(PricesCategoriesFragmentPresenter pricesCategoriesFragmentPresenter);

  void inject(PricesCategoriesAdapterPresenter pricesCategoriesAdapterPresenter);

  void inject(PricesAdapterPresenter pricesAdapterPresenter);

  void inject(PricesFragmentPresenter pricesFragmentPresenter);

  //job
  void inject(NotificationJob notificationJob);

  void inject(JobsCreator jobsCreator);

  //custom views
  void inject(FabNestedScroll scroll);

  void inject(FabRecyclerView view);

  //BroadcastReceivers
  void inject(NetworkBroadcastReceiver networkBroadcastReceiver);
}
