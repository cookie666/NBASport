package com.cookie.NBASport.presenter;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cookie.NBASport.model.MainModel;
import com.cookie.NBASport.util.DimenUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
public class MainPresenter extends BasePresenter {
    private MainModel mainModel;

    @Inject
    public MainPresenter(Context context) {
        super(context);
        mainModel = new MainModel();
    }

    public List<View> getIndicatorViews(){
        List<String> list = mainModel.getIndicatorTitle();
        List<View> listViews = new ArrayList();
        int padding = ((int)DimenUtil.dpToPx(16));
        for(int i= 0;i<list.size();i++){
            TextView textView = new TextView(context);
            //textView.setPadding(padding,0,padding,0);
            textView.setText(list.get(i));
            textView.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(padding,0,padding,0);
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(14);
            listViews.add(textView);
        }
        return  listViews;
    }

    public List<String> getIndicatorTitle(){
        List<String> list = mainModel.getIndicatorTitle();
        return  list;
    }
}
