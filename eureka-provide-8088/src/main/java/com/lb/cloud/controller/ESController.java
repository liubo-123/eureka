package com.lb.cloud.controller;

import com.github.pagehelper.Page;
import com.lb.cloud.dto.ESInfo;
import com.lb.cloud.dto.ResultMap;
import com.lb.cloud.service.EsService;
import com.lb.cloud.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "ESController", description = "商品信息查询")
@Controller
@RequestMapping("/mall")
public class ESController {
    @Autowired
    private EsService es;
    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/product", method = RequestMethod.GET)
    @ResponseBody
    public String search(@RequestParam(required = false) String keywords,
                         @RequestParam(required = false) Integer id,
                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {

        ResultMap map =new ResultMap();
        try {
        Page<ESInfo> ESInfoPage = es.search(keywords,id,pageNum, pageSize);
            map.setMsg("请求成功");
            map.setRes(1);
            map.setO(ESInfoPage);
        }catch (Exception e){
            e.printStackTrace();
            map.setMsg("系统异常");
            map.setRes(0);
        }
        return JsonUtils.toJson(map);
    }
}
