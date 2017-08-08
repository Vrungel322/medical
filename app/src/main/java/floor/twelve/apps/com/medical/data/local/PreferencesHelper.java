package floor.twelve.apps.com.medical.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "com.medical.floor";

  public static final String PREF_THEME_SELECTED = "PREF_THEME_SELECTED";
  public static final String PREF_LANGUAGE_CODE = "PREF_LANGUAGE_CODE";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public int getThemeSelected() {
    return mPreferences.getInt(PREF_THEME_SELECTED, 0);
  }

  public void setThemeSelected(int themeSelected) {
    mPreferences.edit().putInt(PREF_THEME_SELECTED, themeSelected).apply();
  }
}
