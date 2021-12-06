package com.example.contact.net.response;

/**
 * @author: YH
 * @date: 2021/12/3
 * @desc:
 */
public class BaseResModel<T> {
    private Integer code = 0;
    private String msg = null;
    private Data<T> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }
}

