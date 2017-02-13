package com.cookie.NBASport.presenter;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import android.content.Context;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
public class BasePresenter{
   /* @Inject
    T mainModel;*/

    Context context;
    public BasePresenter(Context context){
        this.context = context;
        //DaggerPresenterComponent.builder().build().inject((BasePresenter<BaseModel>) this);
    }


}
