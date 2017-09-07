package floor.twelve.apps.com.medical.feature.doctors.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.feature.doctors.presenters.FeedbackDialogPresenter;
import floor.twelve.apps.com.medical.feature.doctors.views.IFeedbackDialogView;

/**
 * Created by alexandersvyatetsky on 7/09/17.
 */

public class FeedbackDialog extends MvpDialogFragment implements IFeedbackDialogView {

  @InjectPresenter FeedbackDialogPresenter mPresenter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.dialog_feedback, container, false);
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    return new Dialog(getActivity(), R.style.FeedbackDialog);
  }
}
