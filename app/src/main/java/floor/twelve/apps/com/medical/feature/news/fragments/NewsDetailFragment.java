package floor.twelve.apps.com.medical.feature.news.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.authorization.utils.Constants;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import floor.twelve.apps.com.medical.R;
import floor.twelve.apps.com.medical.base.BaseFragment;
import floor.twelve.apps.com.medical.data.model.NewsEntity;
import floor.twelve.apps.com.medical.feature.news.presenters.DetailNewsFragmentPresenter;
import floor.twelve.apps.com.medical.feature.news.views.INewsDetailFragmentView;
import floor.twelve.apps.com.medical.feature.start_point.activities.StartActivity;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class NewsDetailFragment extends BaseFragment implements INewsDetailFragmentView {

  @InjectPresenter DetailNewsFragmentPresenter mDetailNewsFragmentPresenter;

  @BindView(R.id.imageViewPhotoNews) ImageView mImageViewPhotoNews;
  @BindView(R.id.textViewDescriptionNews) TextView mTextViewDescriptionNews;
  @BindView(R.id.textViewTitleNews) TextView mTextViewTitleNews;

  public NewsDetailFragment() {
    super(R.layout.fragment_detail_news);
  }

  public static NewsDetailFragment newInstance(NewsEntity newsEntity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.NEWS_DETAIL_KEY, newsEntity);
    NewsDetailFragment fragment = new NewsDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_news);

    /* turn off scrolling */
    Toolbar mToolbar = getActivity().findViewById(R.id.toolbar);

    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(0);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    NewsEntity newsEntity =
        getArguments().getParcelable(Constants.FragmentsArgumentKeys.NEWS_DETAIL_KEY);

    if (newsEntity != null) {
      Glide.with(mContext)
          .load(newsEntity.getImg())
          .placeholder(
              AppCompatResources.getDrawable(mContext, R.drawable.ic_news_placeholder_130dp))
          .dontAnimate()
          .into(mImageViewPhotoNews);
      mTextViewTitleNews.setText(newsEntity.getTitle());
      mTextViewDescriptionNews.setText(Html.fromHtml(newsEntity.getText()));
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mNavigator.isEmptyBackStack(((MvpAppCompatActivity) getActivity()))) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
    }
  }
}
