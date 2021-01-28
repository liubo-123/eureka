package com.lb.cloud.mogodb;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * 浏览历史Repository
 * Created by macro on 2018/8/3.
 */
public interface MogoDbRepository extends MongoRepository<History,String> {
    /**
     * 根据id按时间倒序获取浏览记录
     * @param memberId 会员id
     */
    List<History> findByMemberIdOrderByCreateTimeDesc(Long memberId);

    /**
     *
     * @param history
     * @return
     */
    History save(History history);


}
