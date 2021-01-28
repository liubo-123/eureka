package com.lb.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mall")
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清除session
        session.invalidate();
        return "注销成功";
    }
    @RequestMapping("/search")
    public String search(){
        return "search";
    }
    @RequestMapping("/echarts")
    public String echarts(){
        return "echarts";
    }
}
