package com.cookie.NBASport.base;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cookie.NBASport.di.DaggerFragmentComponent;
import com.cookie.NBASport.di.FragmentComponent;
import com.cookie.NBASport.di.module.FragmentModule;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
public abstract class BaseFragment<T> extends Fragment {
    protected Context context;
    protected View contentView;
    @Inject
    public T mPresenter;
    private FragmentComponent fragmentComponent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(getContentView(),null);
        context = contentView.getContext();

        ButterKnife.bind(this,contentView);
        //create the presenter
        fragmentComponent = DaggerFragmentComponent.builder().fragmentModule(new FragmentModule(context)).build();
        injectFragment(fragmentComponent);
        init();
        return contentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**get fragment content view*/
    protected abstract int getContentView();
    /**inject you need fragment*/
    protected abstract void injectFragment(FragmentComponent fragmentComponent);
    /**init view or config*/
    protected abstract void init();
}
