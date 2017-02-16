package com.cookie.NBASport.http;

import android.text.TextUtils;

import com.cookie.NBASport.bean.NewsDetail;
import com.cookie.NBASport.util.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chen Lin Jiang on 2017/2/15.
 */

public abstract class RequestCallback implements Callback<String>{
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response != null && !TextUtils.isEmpty(response.body())) {
            String jsonStr = response.body();
            int code = JsonParser.getResultCode(jsonStr);
            if(code == 0){
                onSuccess(jsonStr);
            }else{
                onError(jsonStr);
            }

        } else {
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        onError(t.getMessage());
    }

    public abstract void onSuccess(String result);

    public abstract void onError(String result);
}
