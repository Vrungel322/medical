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
  private static boolean isNetworkChangeState = false;

  public NetworkBroadcastReceiver() {
    App.getAppComponent().inject(this);
  }

  @Override public void onReceive(Context context, Intent intent) {
    Timber.d("NetworkBroadcastReceiver - onReceive");
    if (NetworkUtil.isNetworkConnected(context)) {
      //hide
      isNetworkChangeState = false;
    } else if (!isNetworkChangeState) {
      //show
      isNetworkChangeState = true;
      mRxBus.post(new RxBusHelper.NoInternetAlertDialog());
    }
  }
}
