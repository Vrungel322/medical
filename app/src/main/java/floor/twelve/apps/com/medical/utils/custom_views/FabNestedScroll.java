package floor.twelve.apps.com.medical.utils.custom_views;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import floor.twelve.apps.com.medical.App;
import floor.twelve.apps.com.medical.utils.RxBus;
import floor.twelve.apps.com.medical.utils.RxBusHelper;
import javax.inject.Inject;

/**
 * Created by Alexandra on 12.07.2017.
 */

public class FabNestedScroll extends NestedScrollView {

  @Inject RxBus mRxBus;

  public FabNestedScroll(Context context) {
    super(context);
    App.getAppComponent().inject(this);
    setOnScrollChangeListener(new FabNestedScrollListener());
  }

  public FabNestedScroll(Context context, AttributeSet attrs) {
    super(context, attrs, 0);
    App.getAppComponent().inject(this);
    setOnScrollChangeListener(new FabNestedScrollListener());
  }

  public FabNestedScroll(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    App.getAppComponent().inject(this);
    setOnScrollChangeListener(new FabNestedScrollListener());
  }

  private class FabNestedScrollListener implements OnScrollChangeListener {
    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
        int oldScrollY) {
      if (scrollY > oldScrollY) {
        mRxBus.post(new RxBusHelper.HideFloatingButton());
      }
      if (scrollY < oldScrollY) {
        mRxBus.post(new RxBusHelper.ShowFloatingButton());
      }
    }
  }
}
