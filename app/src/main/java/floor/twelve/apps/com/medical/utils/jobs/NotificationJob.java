package floor.twelve.apps.com.medical.utils.jobs;

import android.support.annotation.NonNull;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.evernote.android.job.Job;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.data.DataManager;
import javax.inject.Inject;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class NotificationJob extends Job {

  @Inject DataManager mDataManager;
  @Inject AuthorizationManager mAuthManager;

  public NotificationJob() {
    App.getAppComponent().inject(this);
  }

  @Override @NonNull protected Result onRunJob(Params params) {

    //boolean showNotification = mAuthManager.isAuthorized();
    //if (showNotification) {
    //  switch (params.getExtras().getString(NOTIFICATION_TYPE, "")) {
    //    case HOURLY:
    //      showNotification = mDataManager.isHourlyNotificationsEnabled();
    //      break;
    //    case DAILY:
    //      showNotification = mDataManager.isDailyNotificationsEnabled();
    //      break;
    //  }
    //}
    //
    //if (showNotification) {
    //
    //  String message = "";
    //  switch (params.getExtras().getString(NOTIFICATION_TYPE, "")) {
    //    case HOURLY:
    //      message = getContext().getString(R.string.notification_text,
    //          getContext().getString(R.string.notification_hourly),
    //          params.getExtras().getString(SERVICE_NAME, ""));
    //      break;
    //    case DAILY:
    //      message = getContext().getString(R.string.notification_text,
    //          getContext().getString(R.string.notification_daily),
    //          params.getExtras().getString(SERVICE_NAME, ""));
    //      break;
    //  }
    //
    //  PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,
    //      new Intent(getContext(), StartActivity.class).setAction(
    //          Long.toString(System.currentTimeMillis())), PendingIntent.FLAG_UPDATE_CURRENT);
    //
    //  Uri uriSound = Uri.parse(
    //      "android.resource://" + getContext().getPackageName() + "/" + R.raw.sound_notification);
    //
    //  Notification notification = new NotificationCompat.Builder(getContext()).setContentTitle(
    //      getContext().getResources().getString(R.string.notification_title))
    //      .setContentText(message)
    //      .setAutoCancel(true)
    //      .setContentIntent(pendingIntent)
    //      .setSmallIcon(R.drawable.ic_create_booking)
    //      .setShowWhen(true)
    //      .setSound(uriSound)
    //      .setLocalOnly(true)
    //      .build();
    //
    //  NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);
    //}
    return Result.SUCCESS;
  }
}