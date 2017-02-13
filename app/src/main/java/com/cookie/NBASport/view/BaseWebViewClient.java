package com.cookie.NBASport.view;/**
 * Created by Chen Lin Jiang on 2017/2/7.
 */

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * User: Chen Lin Jiang
 * Date: 2017-02-07
 */
public class BaseWebViewClient extends WebViewClient {
    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:function setTop(){document.querySelector('.ad-footer').style.display=\"none\";}setTop();");
    }
}
