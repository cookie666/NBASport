package com.cookie.NBASport.ui.main;/**
 * Created by Chen Lin Jiang on 2017/2/8.
 */

import android.widget.ListView;

import com.cookie.NBASport.R;
import com.cookie.NBASport.base.BaseFragment;
import com.cookie.NBASport.di.FragmentComponent;

import butterknife.BindView;

/**
 * User: Chen Lin Jiang
 * Date: 2017-02-08
 */
public class BestBallFragment<T> extends BaseFragment {
    @BindView(R.id.listViewContent)
    ListView listView;
    @Override
    protected int getContentView() {
        return R.layout.fragment_best_ball;
    }

    @Override
    protected void injectFragment(FragmentComponent fragmentComponent) {

    }

    @Override
    protected void init() {

    }
}
