//package com.lb.cloud.service.impl;
//
//import com.github.pagehelper.Page;
//import com.lb.cloud.dto.ESdto;
//import com.lb.cloud.es.EsInfo;
//import com.lb.cloud.es.EsProductRepository;
//import com.lb.cloud.mapper.EsMapper;
//import com.lb.cloud.service.EsService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
///**
//     * 商品搜索管理Service实现类
//     * Created by macro on 2018/6/19.
//     */
//    @Service
//    public class EsServiceImpl implements EsService {
//        private static final Logger LOGGER = LoggerFactory.getLogger(EsServiceImpl.class);
////        @Autowired
////        private EsMapper mapper;
//        @Autowired
//        private EsProductRepository productRepository;
//
////        @Override
////        public int importAll() {
////            List<EsInfo> EsInfoList = mapper.getAllEsInfoList(null);
////            Iterable<EsInfo> EsInfoIterable = productRepository.saveAll(EsInfoList);
////            Iterator<EsInfo> iterator = EsInfoIterable.iterator();
////            int result = 0;
////            while (iterator.hasNext()) {
////                result++;
////                iterator.next();
////            }
////            return result;
////        }
//
////        @Override
////        public void delete(Long id) {
////            productRepository.deleteById(id);
////        }
//
////        @Override
////        public EsInfo create(Long id) {
////            EsInfo result = null;
////            List<EsInfo> EsInfoList = mapper.getAllEsInfoList(id);
////            if (EsInfoList.size() > 0) {
////                EsInfo EsInfo = EsInfoList.get(0);
////                result = productRepository.save(EsInfo);
////            }
////            return result;
////        }
//
////        @Override
////        public void delete(List<Long> ids) {
////            if (!CollectionUtils.isEmpty(ids)) {
////                List<EsInfo> EsInfoList = new ArrayList<>();
////                for (Long id : ids) {
////                    EsInfo EsInfo = new EsInfo();
////                    EsInfo.setId(id);
////                    EsInfoList.add(EsInfo);
////                }
////                productRepository.deleteAll(EsInfoList);
////            }
////        }
//
//        @Override
//        public Page<EsInfo> search(String keyword, Integer pageNum, Integer pageSize) {
//            Pageable pageable = PageRequest.of(pageNum, pageSize);
//            return productRepository.findByNameOrSubTitleOrKeywords(keyword, pageable);
//        }
//
//    }