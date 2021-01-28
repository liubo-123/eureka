package com.lb.cloud.controller;

import com.lb.cloud.service.Impl.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HystrixController {
    @Autowired
    HystrixService service;
    @RequestMapping("/hystrix")
    @ResponseBody
    public String query(){
        return service.query();
    }
    @RequestMapping("/cache")
    @ResponseBody
    public String getUserCache(){
        return service.getUserCache();
    }
}
