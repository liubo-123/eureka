package com.lb.cloud.service.Impl;

import com.lb.cloud.anno.IsOk;
import com.lb.cloud.dao.UserMapper;
import com.lb.cloud.dto.Memu;
import com.lb.cloud.dto.Permission;
import com.lb.cloud.dto.Role;
import com.lb.cloud.dto.User;
import com.lb.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper mapper;
    @Override
    @IsOk("is ok")
    public List<User> queryUserByName(String name,Integer pageNum,Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return mapper.queryUserByName(name,pageable);
    }

    @Override
    public List<Role> queryRole(User u) {
        return mapper.queryRole(u);
    }

    @Override
    public List<Permission> queryPermission(Role r) {
        return mapper.queryPermission(r);
    }

    @Override
    public List<Memu> queryMemu(Permission p) {
        return mapper.queryMemu(p);
    }

    @Override
    public List<User> login(String name, String password) {
        return mapper.login(name,password);
    }

    @Override
    public int register(String name, String password) {
        return mapper.register(name,password);
    }

    @Override
    public List<User> findUserByName(String name) {
        return mapper.findUserByName(name);
    }
}
