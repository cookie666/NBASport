package com.cookie.NBASport.ui.main;/**
 * Created by Chen Lin Jiang on 2017/1/18.
 */

import com.cookie.NBASport.R;
import com.cookie.NBASport.base.BaseFragment;
import com.cookie.NBASport.di.FragmentComponent;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-18
 */
public class NewsFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void injectFragment(FragmentComponent fragmentComponent) {
        //fragmentComponent.inject(this);
    }

    @Override
    protected void init() {

    }
}
