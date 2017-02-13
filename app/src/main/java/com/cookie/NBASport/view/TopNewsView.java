package com.cookie.NBASport.view;/**
 * Created by Chen Lin Jiang on 2017/1/23.
 */

import com.cookie.NBASport.bean.NewsItemEntity;

import java.util.List;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-23
 */
public interface TopNewsView extends BaseView{
    public void getIndexData(List<NewsItemEntity.NewsItem> list);

    public void getLoadMoreData(List<NewsItemEntity.NewsItem> list);

    public void fail(String msg);

}
