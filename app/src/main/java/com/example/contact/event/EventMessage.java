package com.example.contact.event;

/**
 * @author: YH
 * @date: 2021/12/3
 * @desc:
 */
public class EventMessage {

    private EventCode code;
    private String msg = "";
    private String arg1 = "";
    private Integer arg2 = 0;
    private Object obj = null;

    public EventCode getCode() {
        return code;
    }

    public void setCode(EventCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public Integer getArg2() {
        return arg2;
    }

    public void setArg2(Integer arg2) {
        this.arg2 = arg2;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
