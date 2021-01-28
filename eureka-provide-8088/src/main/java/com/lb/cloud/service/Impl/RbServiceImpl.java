package com.lb.cloud.service.Impl;

import com.lb.cloud.dto.Msg;
import com.lb.cloud.send.Sender;
import com.lb.cloud.service.RbSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RbServiceImpl implements RbSevice {
    private static Logger LOGGER = LoggerFactory.getLogger(RbServiceImpl.class);
    @Autowired
    private Sender sender;
    @Override
    public Msg saveMsg(String msg, int id,String status) {
        //生成订单无任何操作订单过期时删除订单
        sendDelayMessageCancel(id);
        Msg ms = new Msg();
        ms.setId(id);
        ms.setMsg(msg);
        ms.setStatus(status);
        return ms;
    }
    //是否支付成功

    @Override
    public void cancelMSG(int id) {
        LOGGER.info("process cancelOrder id:{}"+id);
    }

    @Override
    public Msg updateMsg(Msg msg) {
        msg.setStatus("1");
        return msg;
    }

    private void sendDelayMessageCancel(int id) {
        //获取订单超时时间
        long delayTimes = 30 * 1000;
        //发送延迟消息
        sender.sendMessage(id, delayTimes);
    }
}
