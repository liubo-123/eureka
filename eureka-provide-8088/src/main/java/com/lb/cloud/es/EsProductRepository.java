package com.lb.cloud.es;//package com.lb.cloud.es;

import com.github.pagehelper.Page;
import com.lb.cloud.dto.ESInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 * Created by macro on 2018/6/19.
 */
public interface EsProductRepository extends ElasticsearchRepository<ESInfo, Long> {
    /**
     * @param name
     * @param subtitle
     * @param keywords
     * @param page
     * @return
     */
    Page<ESInfo> findByNameOrSubtitleOrKeywordsOrId(String name, String subtitle, String keywords,Integer id, Pageable page);
}