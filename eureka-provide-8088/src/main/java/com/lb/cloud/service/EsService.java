package com.lb.cloud.service;

import com.github.pagehelper.Page;
import com.lb.cloud.dto.ESInfo;

public interface EsService {
    Page<ESInfo> search(String keyword, Integer id, Integer pageNum, Integer pageSize);
}
