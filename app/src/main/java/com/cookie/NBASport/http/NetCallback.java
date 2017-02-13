package com.cookie.NBASport.http;/**
 * Created by Chen Lin Jiang on 2017/1/19.
 */

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-19
 */
public  interface NetCallback<T> {

    public void onSuccess(T t);
    public void onFail(String msg);
}
