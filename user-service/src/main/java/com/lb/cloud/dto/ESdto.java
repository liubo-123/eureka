package com.lb.cloud.dto;

public class ESdto {
    private int id;
    private String user_infoname;
    private String birthday;
    private String sex;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_infoname() {
        return user_infoname;
    }

    public void setUser_infoname(String user_infoname) {
        this.user_infoname = user_infoname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
