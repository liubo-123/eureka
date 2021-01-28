package com.lb.com.mapper;

import com.github.pagehelper.Page;
import com.lb.com.dto.ESInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ESRepository extends ElasticsearchRepository<ESInfo, Integer> {
    /**
     * @param name
     * @param subTitle
     * @param keywords
     * @param page
     * @return
     */
    Page<ESInfo> findByNameOrSubTitleOrKeywordsOrId(String name, String subTitle, String keywords,Integer id, Pageable page);
}
