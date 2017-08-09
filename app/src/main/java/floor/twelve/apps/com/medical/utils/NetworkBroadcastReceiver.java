package floor.twelve.apps.com.medical.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import floor.twelve.apps.com.medical.App;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Vrungel on 25.07.2017.
 */

public class NetworkBroadcastReceiver extends BroadcastReceiver {
  @Inject RxBus mRxBus;

  @Override public void onReceive(Context context, Intent intent) {
    App.getAppComponent().inject(this);
    Timber.e("NetworkBroadcastReceiver - onReceive");
    if (NetworkUtil.isNetworkConnected(context)) {
      //hide
    } else {
      //show
      mRxBus.post(new RxBusHelper.NoInternetAlertDialog());
    }
  }
}