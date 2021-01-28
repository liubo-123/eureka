package com.lb.cloud.service.Impl;

import com.github.pagehelper.Page;
import com.lb.cloud.dto.ESInfo;
import com.lb.cloud.es.EsProductRepository;
import com.lb.cloud.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class EsServiceImpl implements EsService {
    @Autowired
    private EsProductRepository esRepository;
    @Override
    public Page<ESInfo> search(String keyword, Integer id,Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esRepository.findByNameOrSubtitleOrKeywordsOrId(keyword, keyword, keyword ,id,pageable);
    }

}
