//package com.lb.cloud.service;
//
//import com.github.pagehelper.Page;
//import com.lb.cloud.dto.ESdto;
//import com.lb.cloud.es.EsInfo;
//
//import java.util.List;
//
///**
// * 商品搜索管理Service
// * Created by macro on 2018/6/19.
// */
//public interface EsService {
//    /**
//     * 从数据库中导入所有商品到ES
//     */
////    int importAll();
//
//    /**
//     * 根据id删除商品
//     */
////    void delete(Long id);
//
//    /**
//     * 根据id创建商品
//     */
////    EsInfo create(Long id);
//
//    /**
//     * 批量删除商品
//     */
////    void delete(List<Long> ids);
//
//    /**
//     * 根据关键字搜索名称或者副标题
//     * @return
//     */
//    Page<EsInfo> search(String keyword, Integer pageNum, Integer pageSize);
//
//}