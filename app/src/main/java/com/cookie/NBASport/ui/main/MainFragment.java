package com.cookie.NBASport.ui.main;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
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

    @BindView(R.id.viewPagerContent)
    ViewPager viewPagerContent;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private List<Fragment> fragmentLists = new ArrayList<Fragment>();


    @Override
    protected int getContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        getFragments();
        tabLayout.setupWithViewPager(viewPagerContent);
        setupViewPager(viewPagerContent);
        viewPagerContent.setOffscreenPageLimit(2);


        //initViewPager();
    }

    private void setupViewPager(ViewPager viewPager) {
        List<String> listTitle = mPresenter.getIndicatorTitle();
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(fragmentLists.get(0),listTitle.get(0));
        adapter.addFragment(fragmentLists.get(1), listTitle.get(1));
        viewPager.setAdapter(adapter);
    }


     class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    protected void injectFragment(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
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
}
