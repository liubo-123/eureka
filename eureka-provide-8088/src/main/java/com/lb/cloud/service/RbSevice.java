package com.lb.cloud.service;

import com.lb.cloud.dto.Msg;
import org.springframework.transaction.annotation.Transactional;


public interface RbSevice {


    /**
     * 根据提交信息生成订单
     */
    @Transactional
    Msg saveMsg(String msg, int id,String status);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelMSG(int id);

    /**
     *
     * @return
     */
    Msg updateMsg(Msg msg);

}
