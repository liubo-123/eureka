package com.lb.cloud.controller;

import com.lb.cloud.dto.Msg;
import com.lb.cloud.service.RbSevice;
import com.lb.cloud.util.JsonUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "RbController", description = "消息管理")
@RequestMapping("/rb")
public class RbController {
    private static Logger LOGGER = LoggerFactory.getLogger(RbController.class);
    @Autowired
    private RbSevice rbSevice;
    @RequestMapping("/queryMsg")
    @ResponseBody
    public String queryMsg(int id,String msg,String status) {
        Msg ms = rbSevice.saveMsg(msg,id,status);
        LOGGER.info(JsonUtils.toJson(ms));
        return JsonUtils.toJson(ms);
    }
}
