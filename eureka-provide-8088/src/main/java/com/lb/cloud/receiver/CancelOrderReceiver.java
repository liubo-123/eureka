package com.lb.cloud.receiver;

import com.lb.cloud.service.RbSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 * Created by macro on 2018/9/14.
 */
@Component

public class CancelOrderReceiver {
    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderReceiver.class);
    @Autowired
    private RbSevice rbSevice;
    @RabbitListener(queues = "mall.order.cancel")
    @RabbitHandler
    public void handle(int orderId){
        LOGGER.info("receive delay message orderId:{}",orderId);
        if(orderId==1){

        }
        //过期删除订单信息
        rbSevice.cancelMSG(orderId);
    }
}