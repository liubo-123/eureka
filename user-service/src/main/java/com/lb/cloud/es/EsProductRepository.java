//package com.lb.cloud.es;
//
//import com.github.pagehelper.Page;
//import com.lb.cloud.dto.ESdto;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
///**
// * 商品ES操作类
// * Created by macro on 2018/6/19.
// */
//public interface EsProductRepository extends ElasticsearchRepository<ESdto, Long> {
//    /**
//     * 搜索查询
//     *
//     * @param name              商品名称
//     * @param page              分页信息
//     * @return
//     */
//    Page<EsInfo> findByNameOrSubTitleOrKeywords(String name, Pageable page);
//
//}