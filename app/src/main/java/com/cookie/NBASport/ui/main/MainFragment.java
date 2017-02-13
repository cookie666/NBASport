package com.cookie.NBASport.ui.main;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cookie.NBASport.R;
import com.cookie.NBASport.base.BaseFragment;
import com.cookie.NBASport.di.FragmentComponent;
import com.cookie.NBASport.http.NetApi;
import com.cookie.NBASport.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
public class MainFragment extends BaseFragment<MainPresenter> {
    @BindView(R.id.indicatorHScrollView)
    HorizontalScrollView indicatorHScrollView;
    @BindView(R.id.linearIndicator)
    LinearLayout linearIndicator;
    @BindView(R.id.viewPagerContent)
    ViewPager viewPagerContent;
    @BindView(R.id.indicatorLine)
    TextView indicatorLine;

    private List<Fragment> fragmentLists = new ArrayList<Fragment>();


    @Override
    protected int getContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        addIndicatorChildView();
        initIndicatorLine();
        getFragments();
        initViewPager();
    }

    @Override
    protected void injectFragment(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    private void addIndicatorChildView() {
        List<View> listViews = mPresenter.getIndicatorViews();
        for (int i = 0; i < listViews.size(); i++) {
            listViews.get(i).setTag(i);
            listViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (Integer) v.getTag();
                    viewPagerContent.setCurrentItem(index);
                }
            });
            linearIndicator.addView(listViews.get(i));
        }
    }

    private void initIndicatorLine() {
        ViewTreeObserver viewTreeObserver = linearIndicator.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] xy = new int[2];
                linearIndicator.getChildAt(0).getLocationOnScreen(xy);
                indicatorLine.setWidth(linearIndicator.getChildAt(0).getWidth());
                indicatorLine.setTranslationX(xy[0]);
            }
        });
    }

    private void getFragments() {
        String types[] = {NetApi.BANNER, NetApi.DEPTH};
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            TopNewsFragment topNewsFragment = new TopNewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TopNewsFragment.TYPE, type);
            topNewsFragment.setArguments(bundle);
            fragmentLists.add(topNewsFragment);
        }
    }

    private void initViewPager() {
        viewPagerContent.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentLists.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentLists.get(position);
            }
        });

        viewPagerContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels != 0) {
                    int everyWidth = linearIndicator.getWidth() / linearIndicator.getChildCount();
                    int offsetX = everyWidth * (position);
                    int tempScrollX = (int) (everyWidth * positionOffset);

                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) indicatorLine.getLayoutParams();
                    layoutParams.setMargins((int) (tempScrollX + offsetX), 0, 0, 0);
                    indicatorLine.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
