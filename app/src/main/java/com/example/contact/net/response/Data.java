package com.example.contact.net.response;

import java.util.ArrayList;

/**
 * @author: YH
 * @date: 2021/12/3
 * @desc:
 */
public class Data<T> {
    private Integer count = 0;
    private ArrayList<T> list = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}
