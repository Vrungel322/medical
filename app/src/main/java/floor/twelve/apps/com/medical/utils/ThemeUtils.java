package floor.twelve.apps.com.medical.utils;

import android.content.Context;
import android.content.SharedPreferences;
import floor.twelve.apps.com.medical.R;

import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_FILE_NAME;
import static floor.twelve.apps.com.medical.data.local.PreferencesHelper.PREF_THEME_SELECTED;
import static floor.twelve.apps.com.medical.utils.Constants.Theme.BLUE;
import static floor.twelve.apps.com.medical.utils.Constants.Theme.GRAY;
import static floor.twelve.apps.com.medical.utils.Constants.Theme.GREEN;
import static floor.twelve.apps.com.medical.utils.Constants.Theme.PINK;
import static floor.twelve.apps.com.medical.utils.Constants.Theme.PURPLE;
import static floor.twelve.apps.com.medical.utils.Constants.Theme.RED;
import static floor.twelve.apps.com.medical.utils.Constants.Theme.YELLOW;

/**
 * Created by John on 20.05.2017.
 */

public class ThemeUtils {

  public static int getThemeNoActionBar(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_SELECTED, 0)) {
      case BLUE:
        return R.style.AppTheme_NoActionBar;
      case PINK:
        return R.style.AppThemeNoActionBarPink;
      case GREEN:
        return R.style.AppThemeNoActionBarGreen;
      case YELLOW:
        return R.style.AppThemeNoActionBarYellow;
      case GRAY:
        return R.style.AppThemeNoActionBarGray;
      case PURPLE:
        return R.style.AppThemeNoActionBarPurple;
      case RED:
        return R.style.AppThemeNoActionBarRed;
      default:
        return R.style.AppTheme_NoActionBar;
    }
  }

  public static int getThemeActionBar(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_SELECTED, 0)) {
      case BLUE:
        return R.style.AppTheme;
      case PINK:
        return R.style.AppThemePink;
      case GREEN:
        return R.style.AppThemeGreen;
      case YELLOW:
        return R.style.AppThemeYellow;
      case GRAY:
        return R.style.AppThemeGray;
      case PURPLE:
        return R.style.AppThemePurple;
      case RED:
        return R.style.AppThemeRed;
      default:
        return R.style.AppTheme;
    }
  }
}
