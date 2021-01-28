package com.lb.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/hello")
public class UserRibbonController {
    @Autowired
    private  RestTemplate  restTemplate;
    @Value("${service-url.user-service}")
    private String userServiceUrl;
    @RequestMapping("/provide")
    @ResponseBody
    public String queryRibbon(){
        String forObject = restTemplate.getForObject(userServiceUrl+"/hello/provide", String.class);
        return forObject;
    }
}
