package com.lb.cloud.dto;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private int id;
    private String name;
    private String code;
    private String level;
    private String parente_code;
    private String state;
    private String create_time;
    private String update_time;
    private String create_id;
    private String update_id;
    private List<Permission> permission;
    private List<User> user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParente_code() {
        return parente_code;
    }

    public void setParente_code(String parente_code) {
        this.parente_code = parente_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public String getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(String update_id) {
        this.update_id = update_id;
    }

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", level='" + level + '\'' +
                ", parente_code='" + parente_code + '\'' +
                ", state='" + state + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", create_id='" + create_id + '\'' +
                ", update_id='" + update_id + '\'' +
                ", permission=" + permission +
                ", user=" + user +
                '}';
    }
}
