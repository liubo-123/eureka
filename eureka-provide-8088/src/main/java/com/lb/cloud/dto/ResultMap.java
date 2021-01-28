package com.lb.cloud.dto;

import java.io.Serializable;
import java.util.List;

public class ResultMap implements Serializable {
    private String code;
    private String msg;
    private List list;
    private Object o;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    private int res;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }


}
