package com.lb.cloud.controller;

import com.lb.cloud.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hello")
@Controller
public class FeignController {
    @Autowired
    private IFeignService service;
    @ResponseBody
    @RequestMapping("/feign")
    public String query(){
     return service.query();
    }
}
