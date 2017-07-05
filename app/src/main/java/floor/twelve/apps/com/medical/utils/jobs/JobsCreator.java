package floor.twelve.apps.com.medical.utils.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.data.DataManager;
import javax.inject.Inject;

import static floor.twelve.apps.com.medical.utils.Constants.Notifications.DAILY;
import static floor.twelve.apps.com.medical.utils.Constants.Notifications.HOURLY;
import static floor.twelve.apps.com.medical.utils.Constants.Notifications.NOTIFICATION_TYPE;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class JobsCreator implements JobCreator {

  @Inject DataManager mDataManager;

  public JobsCreator() {
    App.getAppComponent().inject(this);
  }

  @Override public Job create(String tag) {
    return new NotificationJob();
  }

  public void createNotification(String tag, Long millis, String service) {
    //if (millis - mDataManager.getNotificationHours() > 0) {
    //  createHourly(tag, millis - mDataManager.getNotificationHours(), service.toLowerCase());
    //}
    //if (millis - TimeUnit.DAYS.toMillis(mDataManager.getNotificationDays()) > 0) {
    //  createDaily(tag, millis - TimeUnit.DAYS.toMillis(mDataManager.getNotificationDays()),
    //      service.toLowerCase());
    //}
  }

  private void createHourly(String tag, Long millis, String service) {
    PersistableBundleCompat bundle = new PersistableBundleCompat();
    bundle.putString(NOTIFICATION_TYPE, HOURLY);
    //bundle.putString(SERVICE_NAME, service);
    new JobRequest.Builder(tag).setExact(millis).setRequiresCharging(false).setExtras(bundle)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
  }

  private void createDaily(String tag, Long millis, String service) {
    PersistableBundleCompat bundle = new PersistableBundleCompat();
    bundle.putString(NOTIFICATION_TYPE, DAILY);
    //bundle.putString(SERVICE_NAME, service);
    new JobRequest.Builder(tag).setExact(millis).setRequiresCharging(false).setExtras(bundle)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
  }

  public void cancelJob(String tag) {
    JobManager.instance().cancelAllForTag(tag);
  }
}