package com.lb.cloud.controller;

import com.lb.cloud.model.User;
import com.lb.cloud.model.UserExample;
import com.lb.cloud.service.IUserService;
import com.lb.cloud.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cloud")
@Api(tags = "UserController", description = "用户信息管理")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserService service;
    @ApiOperation("查询用户信息")
    @RequestMapping("/queryUser")
    @ResponseBody
    public String queryUser(HttpServletRequest request){
        LOGGER.info("queryUser===res"+JsonUtils.toJson(service.selectByExample(new UserExample())));
       return JsonUtils.toJson(service.selectByExample(new UserExample()));
    }
    @ApiOperation("新增用户信息")
    @RequestMapping("/insertUser/{name}/{password}")
    @ResponseBody
    public String insertUser(@PathVariable String name,@PathVariable String password){
        User u = new User();
        u.setName(name);
        u.setPassword(password);
        LOGGER.info("insertUse===res:"+JsonUtils.toJson(service.insertSelective(u)));
        if(service.insertSelective(u)==1){
            return "新增用户成功";
        }else{
            return "新增用户失败";
        }
    }
    @ApiOperation("更新用户信息")
    @RequestMapping("/updateUser/{name}/{password}/{id}")
    @ResponseBody
    public String updateUser(@PathVariable String name,@PathVariable String password, @PathVariable long id){
        User u = new User();
        u.setName(name);
        u.setPassword(password);
        u.setId(id);
        LOGGER.info("updateUser===res:"+JsonUtils.toJson(service.updateByPrimaryKeySelective(u)));
        if(service.updateByPrimaryKeySelective(u)==1){
            return "修改用户成功";
        }else{
            return "修改用户失败";
        }
    }
    @ApiOperation("删除用户信息")
    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable long id){
        LOGGER.info("deleteUser===res:"+JsonUtils.toJson(service.deleteByPrimaryKey(id)));
        if(service.deleteByPrimaryKey(id)==1){
            return "删除用户成功";
        }else{
            return "删除用户失败";
        }
    }
    @ApiOperation("测试spring cloud")
    @RequestMapping("/hello/{id}")
    @ResponseBody
    public String hello(@PathVariable int id){
        if(id==1) {
            return "hello user-service";
        }else{
            return "sssssss";
        }
    }
}
