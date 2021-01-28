//package com.lb.cloud.controller;
///**
// * 搜索商品管理Controller
// */
//import com.github.pagehelper.Page;
//import com.lb.cloud.dto.CommonPage;
//import com.lb.cloud.dto.CommonResult;
//import com.lb.cloud.dto.ESdto;
//import com.lb.cloud.es.EsInfo;
//import com.lb.cloud.service.EsService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@Api(tags = "EsController", description = "搜索用户信息")
//@RequestMapping("/esProduct")
//public class EsController {
//    @Autowired
//    private EsService esProductService;
//
////    @ApiOperation(value = "导入所有数据库中商品到ES")
////    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
////    @ResponseBody
////    public CommonResult<Integer> importAllList() {
////        int count = esProductService.importAll();
////        return CommonResult.success(count);
////    }
////
////    @ApiOperation(value = "根据id删除商品")
////    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
////    @ResponseBody
////    public CommonResult<Object> delete(@PathVariable Long id) {
////        esProductService.delete(id);
////        return CommonResult.success(null);
////    }
////
////    @ApiOperation(value = "根据id批量删除商品")
////    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
////    @ResponseBody
////    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
////        esProductService.delete(ids);
////        return CommonResult.success(null);
////    }
//
////    @ApiOperation(value = "根据id创建商品")
////    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
////    @ResponseBody
////    public CommonResult<ESdto> create(@PathVariable Long id) {
////        ESdto esProduct = esProductService.create(id);
////        if (esProduct != null) {
////            return CommonResult.success(esProduct);
////        } else {
////            return CommonResult.failed();
////        }
////    }
//
//    @ApiOperation(value = "简单搜索")
//    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<EsInfo>> search(@RequestParam(required = false) String keyword,
//                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
//                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
//        Page<EsInfo> esProductPage = esProductService.search(keyword, pageNum, pageSize);
//        return CommonResult.success(CommonPage.restPage(esProductPage));
//    }
//
//}