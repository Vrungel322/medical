package com.apps.twelve.floor.authorization.base;

import android.content.Context;
import android.graphics.ColorFilter;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;

public class NoChangeBackgroundTextInputLayout extends TextInputLayout {
  public NoChangeBackgroundTextInputLayout(Context context) {
    super(context);
  }

  public NoChangeBackgroundTextInputLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public NoChangeBackgroundTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public void setError(@Nullable CharSequence error) {
    ColorFilter defaultColorFilter = getBackgroundDefaultColorFilter();
    super.setError(error);
    //Reset EditText's background color to default.
    updateBackgroundColorFilter(defaultColorFilter);
  }

  @Override protected void drawableStateChanged() {
    ColorFilter defaultColorFilter = getBackgroundDefaultColorFilter();
    super.drawableStateChanged();
    //Reset EditText's background color to default.
    updateBackgroundColorFilter(defaultColorFilter);
  }

  /**
   * If {@link #getEditText()} is not null & {@link #getEditText()#getBackground()} is not null,
   * update the {@link ColorFilter} of {@link #getEditText()#getBackground()}.
   *
   * @param colorFilter {@link ColorFilter}
   */
  private void updateBackgroundColorFilter(ColorFilter colorFilter) {
    if (getEditText() != null && getEditText().getBackground() != null) {
      getEditText().getBackground().setColorFilter(colorFilter);
    }
  }

  /**
   * Get the EditText's default background color.
   *
   * @return {@link ColorFilter}
   */
  @Nullable private ColorFilter getBackgroundDefaultColorFilter() {
    ColorFilter defaultColorFilter = null;
    if (getEditText() != null && getEditText().getBackground() != null) {
      defaultColorFilter = DrawableCompat.getColorFilter(getEditText().getBackground());
    }
    return defaultColorFilter;
  }
}