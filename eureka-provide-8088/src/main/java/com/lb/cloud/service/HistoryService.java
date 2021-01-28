package com.lb.cloud.service;

import com.lb.cloud.mogodb.History;

import java.util.List;

public interface HistoryService {
    /**
     * 生成浏览记录
     */
    int create(History memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<History> list(Long memberId);
}
