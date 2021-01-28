package com.lb.com.service.Impl;

import com.github.pagehelper.Page;
import com.lb.com.dao.ESDao;
import com.lb.com.dto.ESInfo;
import com.lb.com.mapper.ESRepository;
import com.lb.com.service.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ESServiceImpl implements ESService {
    @Autowired
    ESRepository mapper;
    @Autowired
    ESDao dao;
    @Override
    public Page<ESInfo> search(String keyword, Integer id,Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return mapper.findByNameOrSubTitleOrKeywordsOrId(keyword, keyword, keyword ,id,pageable);
    }
}
