package com.cookie.NBASport.bean;/**
 * Created by Chen Lin Jiang on 2017/1/19.
 */

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-19
 */
public class HttpEntity {
    private int code;
    private String Message;
    private Object object;
    private String jsonData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObjects(Object object) {
        this.object = object;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
