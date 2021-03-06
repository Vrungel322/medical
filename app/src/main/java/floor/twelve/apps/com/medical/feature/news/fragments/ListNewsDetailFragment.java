package floor.twelve.apps.com.medical.feature.news.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.authorization.utils.Constants;
import com.arellomobile.mvp.MvpAppCompatActivity;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.feature.news.adapters.NewsPagerAdapter;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;
import java.util.ArrayList;

/**
 * Created by John on 11.07.2017.
 */

public class ListNewsDetailFragment extends BaseFragment {

  @BindView(R.id.ViewPagerNews) ViewPager mViewPagerNews;

  public ListNewsDetailFragment() {
    super(R.layout.fragment_list_detail_news);
  }

  public static ListNewsDetailFragment newInstance(ArrayList<NewsEntity> listNews, int position) {
    Bundle args = new Bundle();
    args.putParcelableArrayList(Constants.FragmentsArgumentKeys.ALL_NEWS_DETAIL_KEY, listNews);
    args.putInt(Constants.FragmentsArgumentKeys.POSITION, position);
    ListNewsDetailFragment fragment = new ListNewsDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_news);

    mViewPagerNews.setAdapter(new NewsPagerAdapter(this.getChildFragmentManager(),
        getArguments().getParcelableArrayList(
            Constants.FragmentsArgumentKeys.ALL_NEWS_DETAIL_KEY)));
    mViewPagerNews.setCurrentItem(getArguments().getInt(Constants.FragmentsArgumentKeys.POSITION));
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mNavigator.isEmptyBackStack(((MvpAppCompatActivity) getActivity()))) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
    }
  }
}
