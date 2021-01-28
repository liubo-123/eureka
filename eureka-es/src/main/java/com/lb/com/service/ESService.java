package com.lb.com.service;

import com.github.pagehelper.Page;
import com.lb.com.dto.ESInfo;


public interface ESService {
    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<ESInfo> search(String keyword,Integer id,Integer pageNum, Integer pageSize);



}
