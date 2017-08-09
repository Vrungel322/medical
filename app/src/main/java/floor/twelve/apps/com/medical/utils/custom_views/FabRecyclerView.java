package floor.twelve.apps.com.medical.utils.custom_views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.utils.RxBus;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import javax.inject.Inject;

/**
 * Created by Alexandra on 12.07.2017.
 */

public class FabRecyclerView extends RecyclerView {

  @Inject RxBus mRxBus;

  public FabRecyclerView(Context context) {
    super(context);
    App.getAppComponent().inject(this);
    addOnScrollListener(new FabRecyclerListener());
  }

  public FabRecyclerView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    App.getAppComponent().inject(this);
    addOnScrollListener(new FabRecyclerListener());
  }

  public FabRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    App.getAppComponent().inject(this);
    addOnScrollListener(new FabRecyclerListener());
  }

  private class FabRecyclerListener extends OnScrollListener {
    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      if (dy > 0) {
        mRxBus.post(new RxBusHelper.HideFloatingButton());
      } else if (dy < 0) {
        mRxBus.post(new RxBusHelper.ShowFloatingButton());
      }
    }
  }
}
