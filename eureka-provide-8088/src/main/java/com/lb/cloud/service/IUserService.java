package com.lb.cloud.service;

import com.lb.cloud.dto.Memu;
import com.lb.cloud.dto.Permission;
import com.lb.cloud.dto.Role;
import com.lb.cloud.dto.User;

import java.util.List;

public interface IUserService {
    /**
     * 根据用户名称查询用户信息
     * @param name
     * @return
     */
    List<User> queryUserByName(String name,Integer pageNum,Integer pageSize);

    /**
     *
     * @param u
     * @return
     */
    List<Role> queryRole(User u);
    /**
     *
     * @param r
     * @return
     */
    List<Permission> queryPermission(Role r);
    /**
     *
     * @param p
     * @return
     */
    List<Memu> queryMemu(Permission p);

    /**
     * 登录接口
     * @param name
     * @param password
     * @return
     */
    List<User> login(String name,String password);

    /**
     * 注册接口
     * @param name
     * @param password
     * @return
     */
    int register(String name,String password);

    /**
     *
     * @param name
     * @return
     */
    List<User> findUserByName(String name);
}
