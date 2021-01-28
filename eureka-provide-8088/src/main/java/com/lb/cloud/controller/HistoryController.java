package com.lb.cloud.controller;

import com.lb.cloud.mogodb.History;
import com.lb.cloud.mogodb.HistoryDto;
import com.lb.cloud.service.HistoryService;
import com.lb.cloud.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mall")
@Api(tags = "HistoryController", description = "=用户浏览记录管理")
public class HistoryController {
    private String id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;
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
        public String delete(@RequestParam("ids") List<String> ids) {
            int count = memberReadHistoryService.delete(ids);
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
            List<HistoryDto> list = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<History> memberReadHistoryList = memberReadHistoryService.list(memberId);
            for(History h:memberReadHistoryList){
                HistoryDto dto = new HistoryDto();
                dto.setCreateTime(sdf.format(h.getCreateTime()));
                dto.setId(h.getId());
                dto.setMemberId(h.getMemberId());
                dto.setMemberNickname(h.getMemberNickname());
                dto.setProductId(h.getProductId());
                dto.setProductName(h.getProductName());
                list.add(dto);
            }
            return JsonUtils.toJson(list);
        }
    }
