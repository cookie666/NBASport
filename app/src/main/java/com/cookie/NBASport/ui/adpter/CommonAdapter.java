package com.cookie.NBASport.ui.adpter;/**
 * Created by Chen Lin Jiang on 2017/1/19.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-19
 */
public abstract class CommonAdapter<T> extends BaseAdapter{
    protected List<T> listDatas;
    protected Context context;
    private int layoutId;

    public CommonAdapter(List<T> listDatas,Context context,int layoutId){
        this.listDatas = listDatas;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.getViewHolder(context,convertView,layoutId);
        getConvert(viewHolder,listDatas.get(position));
        return viewHolder.getConvertView();
    }

    public abstract void getConvert(ViewHolder viewHolder, T data);

}
