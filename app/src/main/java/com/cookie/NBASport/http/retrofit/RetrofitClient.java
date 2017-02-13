package com.cookie.NBASport.http.retrofit;/**
 * Created by Chen Lin Jiang on 2017/1/19.
 */

import android.text.TextUtils;

import com.cookie.NBASport.BuildConfig;
import com.cookie.NBASport.bean.HttpEntity;
import com.cookie.NBASport.bean.IndexNewEntity;
import com.cookie.NBASport.bean.NewsDetail;
import com.cookie.NBASport.bean.NewsItemEntity;
import com.cookie.NBASport.bean.VideoInfo;
import com.cookie.NBASport.http.NetCallback;
import com.cookie.NBASport.http.NetClient;
import com.cookie.NBASport.util.JsonParser;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-19
 */
public class RetrofitClient extends NetClient {
    private static Retrofit retrofitGson = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.TENCENT_SERVER).build();
    static Retrofit retrofitScalars = new Retrofit.Builder().baseUrl(BuildConfig.TENCENT_SERVER).addConverterFactory(ScalarsConverterFactory.create()).build();

    @Override
    public NetClient addParams(String param) {
        return null;
    }

    @Override
    public void send() {
    }

    public static void getNewsIndex(String type,final NetCallback netCallback){
        RetrofitService retrofitService = retrofitGson.create(RetrofitService.class);
        Call<IndexNewEntity> call = retrofitService.getNewsIndex(type);
        call.enqueue(new Callback<IndexNewEntity>() {
            @Override
            public void onResponse(Call<IndexNewEntity> call, Response<IndexNewEntity> response) {
                IndexNewEntity indexNewEntity = response.body();
                if(indexNewEntity !=null && indexNewEntity.getCode()==0){
                    netCallback.onSuccess(indexNewEntity);
                }else{
                    netCallback.onFail("加载失败");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                netCallback.onFail("加载失败");
            }
        });
    };

    public static void getNews(String type,final NetCallback netCallback,String articleIds){
        RetrofitService retrofitService = retrofitScalars.create(RetrofitService.class);
        Call<String> call = retrofitService.getNewsItem(type,articleIds);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String jsonStr = response.body();
                    int code = JsonParser.getResultCode(jsonStr);
                    if(code == 0){
                        NewsItemEntity newsItem = JsonParser.parseNewsItem(jsonStr);
                        HttpEntity httpEntity = new HttpEntity();
                        httpEntity.setCode(code);
                        httpEntity.setJsonData(jsonStr);
                        httpEntity.setObjects(newsItem);

                        netCallback.onSuccess(httpEntity);
                    }

                } else {
                    netCallback.onFail("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                netCallback.onFail("加载失败");
            }
        });
    };

    public static void getNewDetail(String type,final NetCallback netCallback,String articleId){
        RetrofitService retrofitService = retrofitScalars.create(RetrofitService.class);
        Call<String> call = retrofitService.getNewsDetail(type,articleId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String jsonStr = response.body();
                    int code = JsonParser.getResultCode(jsonStr);
                    if(code == 0){
                        NewsDetail newsDetail = JsonParser.parseNewsDetail(jsonStr);
                        netCallback.onSuccess(newsDetail);
                    }

                } else {
                    netCallback.onFail("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                netCallback.onFail("加载失败");
            }
        });

    }

    public static void getVideoUrl(String vid,final NetCallback netCallback){
        Retrofit retrofitScalars = new Retrofit.Builder().baseUrl(BuildConfig.TECENT_URL_SERVER_1).addConverterFactory(ScalarsConverterFactory.create()).build();
        RetrofitService retrofitService = retrofitScalars.create(RetrofitService.class);
        Call<String> call = retrofitService.getVideoRealUrls(vid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String resp = response.body()
                            .replaceAll("QZOutputJson=", "")
                            .replaceAll(" ", "")
                            .replaceAll("\n", "");
                    if (resp.endsWith(";"))
                        resp = resp.substring(0, resp.length() - 1);

                    VideoInfo info = new Gson().fromJson(resp, VideoInfo.class);
                    netCallback.onSuccess(info);

                } else {
                    netCallback.onFail("加载失败");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                netCallback.onFail("加载失败");
            }
        });
    }

}
