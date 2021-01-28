package com.lb.cloud.service.Impl;

import com.lb.cloud.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Override
    public String query() {
        return "hello admin-client";
    }
}
