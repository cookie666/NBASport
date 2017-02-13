package com.cookie.NBASport.model;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
public class MainModel extends BaseModel {

    @Inject
    public MainModel(){

    }

    public List getIndicatorTitle(){
        List<String> list = new ArrayList<String>();
        list.add("今日头条");
        list.add("今日最佳");
        return list;
    }
}
