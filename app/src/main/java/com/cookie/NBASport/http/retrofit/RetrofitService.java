package com.cookie.NBASport.http.retrofit;/**
 * Created by Chen Lin Jiang on 2017/1/19.
 */

import com.cookie.NBASport.bean.IndexNewEntity;
import com.cookie.NBASport.http.NetApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-19
 */
public interface RetrofitService {
    @GET(NetApi.NEWS_INDEX)
    Call<IndexNewEntity> getNewsIndex(@Query("column") String column);

    @GET(NetApi.NEWS_ITEM)
    Call<String> getNewsItem(@Query("column") String column, @Query("articleIds") String articleIds);

    @GET(NetApi.NEWS_DETAIL)
    Call<String> getNewsDetail(@Query("column") String column, @Query("articleId") String articleId);

    /**
     * 最新方法
     * http://h5vv.video.qq.com/getinfo?callback=tvp_request_getinfo_callback_340380&platform=11001&charge=0&otype=json&ehost=http%3A%2F%2Fv.qq.com&sphls=0&sb=1&nocache=0&_rnd=1474896074003&vids=m0022ect1qs&defaultfmt=auto&&_qv_rmt=CTWS8OLbA17867igt=&_qv_rmt2=x6oMojAw144904luQ=&sdtfrom=v3010&callback=tvp_request_getinfo_callback_
     *
     * @param vids
     * @return
     */
    @GET("getinfo?platform=11001&charge=0&otype=json")
    Call<String> getVideoRealUrls(@Query("vids") String vids);
}
