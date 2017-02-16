package com.cookie.NBASport.presenter;/**
 * Created by Chen Lin Jiang on 2017/2/4.
 */

import android.content.Context;

import com.cookie.NBASport.bean.NewsDetail;
import com.cookie.NBASport.http.NetApi;
import com.cookie.NBASport.http.NetCallback;
import com.cookie.NBASport.http.retrofit.RetrofitClient;
import com.cookie.NBASport.util.JsonParser;
import com.cookie.NBASport.view.NewDetailView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * User: Chen Lin Jiang
 * Date: 2017-02-04
 */
public class NewDetailPrestener extends BasePresenter {
    @Inject
    public NewDetailPrestener(Context context) {
        super(context);
    }

    public void getNewDetail(String articleId,final NewDetailView newDetailView){
       /* RetrofitClient.getNewDetail(NetApi.BANNER,new NetCallback<NewsDetail>() {
            @Override
            public void onSuccess(NewsDetail newsDetail) {
                newDetailView.getNewDetail(newsDetail);
            }

            @Override
            public void onFail(String msg) {
                newDetailView.onFail(msg);
            }
        },articleId);*/


        RetrofitClient retrofitClient = new RetrofitClient();
        retrofitClient.addParams("column",NetApi.BANNER).addParams("articleId",articleId);
        retrofitClient.Url(NetApi.NEWS_DETAIL);
        retrofitClient.send(new NetCallback<String>() {
            @Override
            public void onSuccess(String o) {
                NewsDetail newsDetail = JsonParser.parseNewsDetail(o);
                newDetailView.getNewDetail(newsDetail);
            }

            @Override
            public void onFail(String msg) {
                newDetailView.onFail(msg);
            }
        });
    }
}
