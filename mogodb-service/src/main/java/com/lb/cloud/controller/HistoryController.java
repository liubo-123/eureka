package com.lb.cloud.controller;

import com.lb.cloud.mogodb.History;
import com.lb.cloud.service.HistoryService;
import com.lb.cloud.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mogodb")
@Api(tags = "HistoryController", description = "=用户浏览记录管理")
public class HistoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);
    @Autowired
        private HistoryService memberReadHistoryService;
        @ApiOperation("创建浏览记录")
        @RequestMapping(value = "/create", method = RequestMethod.POST)
        @ResponseBody
        public String create(HttpServletRequest request) {
            Long memberid=1l;
            Long productid=11l;
            History history = new History();
            history.setId("001");
            history.setMemberId(memberid);
            history.setMemberNickname("test id");
            history.setProductId(productid);
            history.setProductName("test product");
            history.setCreateTime(new Date());
            int count = memberReadHistoryService.create(history);
            if (count > 0) {
                return "success";
            } else {
                return "failed";
            }
        }

        @ApiOperation("删除浏览记录")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        @ResponseBody
        public String delete(@RequestParam("ids") String ids) {
            LOGGER.info("ids:"+ids);
            String[] split = ids.split(",");
            LOGGER.info("split:"+JsonUtils.toJson(split));
            LOGGER.info("list:"+JsonUtils.toJson(Arrays.asList(split)));
            int count = memberReadHistoryService.delete(Arrays.asList(split));
            if (count > 0) {
                return "success";
            } else {
                return "failed";
            }
        }

        @ApiOperation("展示浏览记录")
        @RequestMapping(value = "/list", method = RequestMethod.GET)
        @ResponseBody
        public String list(Long memberId) {
            List<History> memberReadHistoryList = memberReadHistoryService.list(memberId);
            return JsonUtils.toJson(memberReadHistoryList);
        }
    }
