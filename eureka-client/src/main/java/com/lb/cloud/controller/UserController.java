package com.lb.cloud.controller;

import com.lb.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class UserController {
    @Autowired
    IUserService service;
    @RequestMapping("/admin-client")
    @ResponseBody
    public String query(){
        return service.query();
    }
}
