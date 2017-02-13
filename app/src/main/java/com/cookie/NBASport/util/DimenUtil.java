package com.cookie.NBASport.util;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import com.cookie.NBASport.base.App;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
public class DimenUtil {
    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    public static float dpToPx(float dp) {
        return dp * App.getApplication().getResources().getDisplayMetrics().density;
    }

    public static int px2sp(float pxValue) {
        final float fontScale = App.getApplication().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }



    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return App.getApplication().getResources().getDisplayMetrics().widthPixels;
    }
}
