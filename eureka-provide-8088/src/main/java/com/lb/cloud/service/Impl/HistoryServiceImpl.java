package com.lb.cloud.service.Impl;

import com.lb.cloud.mogodb.History;
import com.lb.cloud.mogodb.MogoDbRepository;
import com.lb.cloud.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private MogoDbRepository memberReadHistoryRepository;
    @Override
    public int create(History memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<History> deleteList = new ArrayList<>();
        for(String id:ids){
            History memberReadHistory = new History();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<History> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}