package com.cookie.NBASport.ui.adpter;/**
 * Created by Chen Lin Jiang on 2017/1/29.
 */

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-29
 */
public class ViewHolder {
    private SparseArray<View> views;
    private View convertView;

    private ViewHolder(Context context,int layoutId){
        views = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layoutId,null);
        convertView.setTag(this);
    }

    public static  ViewHolder getViewHolder(Context context ,View convertView,int layoutId){
        ViewHolder viewHolder;
        if(convertView ==null){
             viewHolder = new ViewHolder(context,layoutId);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        return viewHolder;
    }

    public <T extends View>T getView(int viewId){
        View view = views.get(viewId);
        if(view==null){
            view = convertView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T)view;
    }

    public View getConvertView(){
        return convertView;
    }
}
