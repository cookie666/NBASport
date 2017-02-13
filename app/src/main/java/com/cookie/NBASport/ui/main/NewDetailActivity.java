package com.cookie.NBASport.ui.main;/**
 * Created by Chen Lin Jiang on 2017/1/31.
 */

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.cookie.NBASport.R;
import com.cookie.NBASport.base.BaseBannerActivity;
import com.cookie.NBASport.bean.NewsDetail;
import com.cookie.NBASport.di.ActivityComponent;
import com.cookie.NBASport.presenter.NewDetailPrestener;
import com.cookie.NBASport.view.BaseWebViewClient;
import com.cookie.NBASport.view.NewDetailView;

import butterknife.BindView;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-31
 */
public class NewDetailActivity extends BaseBannerActivity<NewDetailPrestener> implements NewDetailView{
    @BindView(R.id.webView)
    WebView webView;

    public static void open(Context context,String articleId){
        Intent intent = new Intent(context,NewDetailActivity.class);
        intent.putExtra("ArticleId",articleId);
        context.startActivity(intent);
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_new_detail;
    }

    @Override
    protected void init() {
        super.init();
        setTitle("详情");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new BaseWebViewClient());


        Intent intent = getIntent();
        String articleId = intent.getStringExtra("ArticleId");
        if(!TextUtils.isEmpty(articleId)){
            mPresenter.getNewDetail(articleId,this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getNewDetail(NewsDetail newsDetail) {
        webView.loadUrl(newsDetail.url);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
