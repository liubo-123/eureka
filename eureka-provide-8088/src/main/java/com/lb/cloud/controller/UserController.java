package com.lb.cloud.controller;

import com.lb.cloud.dto.*;
import com.lb.cloud.service.IUserService;
import com.lb.cloud.util.ExcelUtil;
import com.lb.cloud.util.JsonUtils;
import com.lb.cloud.util.VerifyCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Api(tags = "UserController", description = "用户信息")
@Controller
@RequestMapping("/mall")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserService service;
    @ApiOperation(value = "人员名称查询用户")
    @RequestMapping(value = "/user/queryUserByName/{name}/{pageNum}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public String queryUserByName(@PathVariable String name,
                                  @PathVariable Integer pageNum,
                                  @PathVariable Integer pageSize
){
        ResultMap map = new ResultMap();
        List list = new ArrayList();
        list.add("暂无数据");
        LOGGER.info("name========"+name);
        long sTime=new Date().getTime();
        LOGGER.info("queryUserByName调用开始时间========"+sTime);
        try {
            if (StringUtils.isEmpty(name)) {
                map.setCode("F000001");
                map.setMsg("参数缺失");
            } else {
                List<User> data = service.queryUserByName(name,pageNum,pageSize);
                if (data != null && data.size() > 0) {
                    for (User u :data) {
                        List<Role> roles = service.queryRole(u);
                        u.setRole(roles);
                        if (roles != null && roles.size() > 0) {
                            for (Role r : roles) {
                                List<Permission> permissions = service.queryPermission(r);
                                r.setPermission(permissions);
                                if (permissions != null && permissions.size() > 0) {
                                    for (Permission p : permissions) {
                                        List<Memu> memus = service.queryMemu(p);
                                        p.setMemu(memus);

                                    }
                                }
                            }
                        }
                    }
                    map.setCode("S000001");
                    map.setMsg("请求成功");
                    map.setList(data);
                } else {
                    map.setCode("S000001");
                    map.setMsg("请求成功");
                    map.setList(list);
                }
                long eTime = new Date().getTime();
                long time = eTime - sTime;
                LOGGER.info("queryUserByName调用结束时间========" + eTime + "总耗时===" + time);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.setCode("F000001");
            map.setMsg("系统异常");
        }
        LOGGER.info("返回结果map========"+JsonUtils.toJson(map));
        return JsonUtils.toJson(map);
    }
    @ApiOperation(value = "查询用户")
    @RequestMapping(value = "/user/queryUser",method = RequestMethod.GET)
    @ResponseBody
    public String queryUser(@RequestParam(required = false) String name,
                            @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                            HttpServletRequest request,HttpServletResponse response){
        ResultMap map = new ResultMap();
        List list = new ArrayList();
        list.add("暂无数据");
        LOGGER.info("name========"+name);
        long sTime=new Date().getTime();
        LOGGER.info("queryUser调用开始时间========"+sTime);
        try {
                List<User> user = service.queryUserByName(name,pageNum,pageSize);
                if (user != null && user.size() > 0) {
                    for (User u :user){
                        List<Role> roles = service.queryRole(u);
                        u.setRole(roles);
                        if (roles != null && roles.size() > 0) {
                            for(Role r :roles){
                                List<Permission> permissions = service.queryPermission(r);
                                r.setPermission(permissions);
                                if (permissions != null && permissions.size() > 0) {
                                    for(Permission p :permissions){
                                        List<Memu> memus = service.queryMemu(p);
                                        p.setMemu(memus);
                                    }
                                }
                            }
                        }

                    }
                    String b_name="人员信息统计表.xls";
                    byte[] data= ExcelUtil.write2Excel(user);
                    //把字节数据下载到浏览器客户端
                    try {
                        response.setContentType("application/x-msdownload");
                        //显示下载的条
                        response.setHeader("Content-Disposition", "attachment;fileName="+new String(name.getBytes("gb2312"), "ISO-8859-1") + ".xls");
                        response.setContentLength(data.length);
                        OutputStream os=response.getOutputStream();
                        os.write(data);
                        os.flush();
                        os.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    map.setCode("S000001");
                    map.setMsg("请求成功");
                    map.setList(user);
                } else {
                    map.setCode("S000001");
                    map.setMsg("请求成功");
                    map.setList(list);
                }
                long eTime = new Date().getTime();
                long time = eTime - sTime;
                LOGGER.info("queryUser调用结束时间========" + eTime + "总耗时===" + time);
        }catch (Exception e){
            e.printStackTrace();
            map.setCode("F000001");
            map.setMsg("系统异常");
        }
        LOGGER.info("返回结果map========"+JsonUtils.toJson(map));
        return JsonUtils.toJson(map);
    }
    @ApiOperation(value = "人员登录")
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public String userLogin(@RequestParam String loginName,
                            @RequestParam String password){
        ResultMap map = new ResultMap();
        LOGGER.info("loginName:==="+loginName+",password=====:"+password);
        long sTime=new Date().getTime();
        LOGGER.info("userLogin调用开始时间========"+sTime);
        try {
            List<User> user = service.login(loginName, password);
            LOGGER.info("user:==="+JsonUtils.toJson(user));
            if (user != null && user.size() > 0) {
                map.setCode("S000001");
                map.setMsg("请求成功");
                map.setRes(1);
//           map.setList(user);
            } else {
                map.setCode("S000001");
                map.setMsg("无此用户");
                map.setRes(0);
//            map.setList(user);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.setCode("F000001");
            map.setMsg("系统异常");
        }
        long eTime = new Date().getTime();
        long time = eTime - sTime;
        LOGGER.info("userLogin调用结束时间========" + eTime + "总耗时===" + time);
        LOGGER.info("返回结果map========"+JsonUtils.toJson(map));
        return JsonUtils.toJson(map);
    }
    @ApiOperation(value = "人员注册")
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam String username,@RequestParam String password){
        ResultMap map = new ResultMap();
        LOGGER.info("username:==="+username+",password=====:"+password);
        long sTime=new Date().getTime();
        LOGGER.info("register调用开始时间========"+sTime);
        try {
            int register = service.register(username, password);
            if (register > 0) {
                map.setCode("S000001");
            } else {
                map.setCode("F000001");
            }
            map.setMsg("请求成功");
            map.setRes(register);
        }catch (Exception e){
            e.printStackTrace();
            map.setCode("F000001");
            map.setMsg("系统异常");
        }
        long eTime = new Date().getTime();
        long time = eTime - sTime;
        LOGGER.info("register调用结束时间========" + eTime + "总耗时===" + time);
        LOGGER.info("返回结果map========"+JsonUtils.toJson(map));
        return JsonUtils.toJson(map);
    }
    @ApiOperation(value = "人员是否存在")
    @RequestMapping(value = "/user/findUserByName",method = RequestMethod.GET)
    @ResponseBody
    public String findUserByName(@RequestParam String name){
        ResultMap map = new ResultMap();
        LOGGER.info("username:==="+name);
        long sTime=new Date().getTime();
        LOGGER.info("findUserByName调用开始时间========"+sTime);
        try {
            List<User> userByName = service.findUserByName(name);
            LOGGER.info("userByName========"+JsonUtils.toJson(userByName));
            if (userByName !=null & userByName.size()>0) {
                map.setCode("S000001");
                map.setMsg("用户名已存在");
                map.setRes(1);
            } else {
                map.setCode("S000001");
                map.setMsg("用户名可用");
                map.setRes(0);
            }
            map.setMsg("请求成功");
        }catch (Exception e){
            e.printStackTrace();
            map.setCode("F000001");
            map.setMsg("系统异常");
        }
        long eTime = new Date().getTime();
        long time = eTime - sTime;
        LOGGER.info("findUserByName调用结束时间========" + eTime + "总耗时===" + time);
        LOGGER.info("返回结果map========"+JsonUtils.toJson(map));
        return JsonUtils.toJson(map);
    }
    //动态生成验证码
    @ApiOperation(value = "验证码生成")
    @RequestMapping(value="/user/doGet1",method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 创建VerifyCode的实例
        OutputStream outputStream = resp.getOutputStream();
        VerifyCode vc=new VerifyCode();
        // 调用其方法动态生成一张验证码图片
        HttpSession session = req.getSession();
        vc.drawImage(outputStream);

        // 将图片直接写入到response缓冲区中
        String text=vc.getCode();
        session.setAttribute("code", text);
        // 获取正确的验证码内容
        //System.out.println("验证码="+text);
        // 将正确的验证码内容存入Session作用域
        // 将正确的验证码内容存入ServletContext作用域
        // 多用户情况下会有并发问题
        //this.getServletContext().setAttribute("code", text);
    }
    //前台ajax验证后台验证码
    @ApiOperation(value = "前台ajax验证后台验证码")
    @RequestMapping(value="/user/doGet/valiName/{codeText}",method=RequestMethod.POST)
    @ResponseBody
    public String doGetCode(HttpServletRequest req, HttpServletResponse resp,@PathVariable("codeText") String codeText)
            throws ServletException, IOException{
        ResultMap map = new ResultMap();
        String code=(String) req.getSession().getAttribute("code");
        if((code.toLowerCase()).equals((codeText.toLowerCase()))) {
            map.setRes(1);
            map.setMsg("验证成功！");
        }else {
            map.setRes(0);
            map.setMsg("验证码错误，重新输入！");
        }
        return JsonUtils.toJson(map);

    }
    //前台ajax验证后台验证码
    @ApiOperation(value = "前台ajax验证后台验证码")
    @RequestMapping(value="/user/doGetBack/valiName/{codeText}",method=RequestMethod.POST)
    @ResponseBody
    public String doGetCodeBack(HttpServletRequest req, HttpServletResponse resp,@PathVariable("codeText") String codeText)
            throws ServletException, IOException {
        ResultMap map = new ResultMap();
        String code = (String) req.getSession().getAttribute("code");

		            /*System.out.println("system code " +req.getSession().getAttribute("code"));
		            System.out.println("user code  " + codeText);*/
        if ((code.toLowerCase()).equals((codeText.toLowerCase()))) {
            map.setRes(1);
            map.setMsg("验证成功！");
        } else {
            map.setRes(0);
            map.setMsg("验证码错误，重新输入！");
        }
        return JsonUtils.toJson(map);
    }


}
