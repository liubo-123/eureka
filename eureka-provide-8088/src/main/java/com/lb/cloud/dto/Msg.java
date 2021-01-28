package com.lb.cloud.dto;

import java.io.Serializable;

public class Msg implements Serializable {
    private int id;
    private String msg;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
