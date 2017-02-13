package com.cookie.NBASport.presenter;/**
 * Created by Chen Lin Jiang on 2017/1/20.
 */

import android.content.Context;
import android.text.TextUtils;

import com.cookie.NBASport.bean.HttpEntity;
import com.cookie.NBASport.bean.IndexNewEntity;
import com.cookie.NBASport.bean.NewsItemEntity;
import com.cookie.NBASport.http.NetCallback;
import com.cookie.NBASport.http.retrofit.RetrofitClient;
import com.cookie.NBASport.view.TopNewsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-20
 */
public class TopNewsPresenter extends BasePresenter {
    private List<String> indexs = new ArrayList<>();
    private int start = 0;
    private int size = 10;

    @Inject
    public TopNewsPresenter(Context context) {
        super(context);
    }

    public void getIndexNews(final String type ,final TopNewsView topNewsView){
        start = 0;
        RetrofitClient.getNewsIndex(type,new NetCallback<IndexNewEntity>() {
            @Override
            public void onSuccess(IndexNewEntity indexNewEntity) {
                indexs.clear();
                for(int i=0;i<indexNewEntity.getData().size();i++){
                    indexs.add(indexNewEntity.getData().get(i).getId());
                }
                String articleIds = getArticleIds();
                if(!TextUtils.isEmpty(articleIds)) {
                    RetrofitClient.getNews(type,new NetCallback<HttpEntity>() {
                        @Override
                        public void onSuccess(HttpEntity httpEntity) {
                            NewsItemEntity newsItemEntity = (NewsItemEntity) httpEntity.getObject();
                            topNewsView.getIndexData(newsItemEntity.data);
                        }

                        @Override
                        public void onFail(String msg) {
                            topNewsView.fail("加载失败");

                        }
                    },articleIds);
                }else{
                    topNewsView.fail("加载失败");
                }

            }

            @Override
            public void onFail(String msg) {
                topNewsView.fail("加载失败");

            }
        });
    }

    private String getArticleIds(){
        String articleIds = "";

        for (int i = start,j = 0; i < indexs.size() && j<size ; i++, j++,start++) {
            articleIds += indexs.get(i) + ",";
        }
        if (!TextUtils.isEmpty(articleIds)){
            articleIds = articleIds.substring(0, articleIds.length() - 1);
        }
        return  articleIds;
    }

    public void getLoadMore(String type,final TopNewsView topNewsView){
        String articleIds = getArticleIds();
        if(!TextUtils.isEmpty(articleIds)){
            RetrofitClient.getNews(type,new NetCallback<HttpEntity>() {
                @Override
                public void onSuccess(HttpEntity httpEntity) {
                    NewsItemEntity newsItemEntity = (NewsItemEntity) httpEntity.getObject();
                    topNewsView.getLoadMoreData(newsItemEntity.data);
                }

                @Override
                public void onFail(String msg) {
                    topNewsView.fail("加载失败");
                }
            },articleIds);
        }else{
            topNewsView.fail("加载完");
        };
    }
}
