package floor.twelve.apps.com.medical.feature.news.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.feature.news.fragments.NewsDetailFragment;
import java.util.ArrayList;

/**
 * Created by John on 11.07.2017.
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

  private ArrayList<NewsEntity> mNewsEntities = new ArrayList<>();

  public NewsPagerAdapter(FragmentManager fm, ArrayList<NewsEntity> listNews) {
    super(fm);
    mNewsEntities = listNews;
  }

  @Override public Fragment getItem(int position) {
    return (NewsDetailFragment.newInstance(mNewsEntities.get(position)));
  }

  @Override public int getCount() {
    return mNewsEntities.size();
  }
}
