package com.lb.cloud.send;

import com.lb.cloud.dto.QueueEnum;
import com.lb.cloud.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private static Logger LOGGER =LoggerFactory.getLogger(Sender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;
    //查询所有未支付的订单
    public void sendMessage(int orderId,final long delayTimes){
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //给消息设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                LOGGER.info("send delay message orderId:{}"+ JsonUtils.toJson(message));
                return message;

            }
        });
        LOGGER.info("send delay message orderId:{}",orderId);
    }
}
