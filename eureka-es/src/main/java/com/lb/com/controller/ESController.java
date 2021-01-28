package com.lb.com.controller;

import com.github.pagehelper.Page;
import com.lb.com.dto.ESInfo;
import com.lb.com.service.ESService;
import com.lb.com.util.JsonUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/es")
public class ESController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESController.class);
    @Autowired
    private ESService service;
    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public String search(@RequestParam(required = false) String keywords,
                         @RequestParam(required = false) Integer id,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<ESInfo> ESInfoPage = service.search(keywords,id,pageNum, pageSize);
        return JsonUtils.toJson(ESInfoPage);
    }


}
