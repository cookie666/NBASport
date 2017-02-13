package com.cookie.NBASport.view;/**
 * Created by Chen Lin Jiang on 2017/2/4.
 */

import com.cookie.NBASport.bean.NewsDetail;

/**
 * User: Chen Lin Jiang
 * Date: 2017-02-04
 */
public interface NewDetailView extends BaseView {
    public void getNewDetail(NewsDetail newsDetail);
    public void onFail(String msg);
}
