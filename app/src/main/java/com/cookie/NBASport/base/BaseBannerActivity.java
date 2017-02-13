package com.cookie.NBASport.base;/**
 * Created by Chen Lin Jiang on 2017/2/4.
 */

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cookie.NBASport.R;

/**
 * User: Chen Lin Jiang
 * Date: 2017-02-04
 */
public abstract class  BaseBannerActivity<T> extends BaseActivity<T>{
    private ImageButton butBack;
    private TextView tvTitle;

    @Override
    protected void init() {
        initBanner();
    }

    private void initBanner(){
        butBack = (ImageButton)findViewById(R.id.butBack);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setTitle(String title){
        tvTitle.setText(title);
    };
}
