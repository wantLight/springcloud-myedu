package com.wsq.edu.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-9-12 17:53
 */
@Component("userOrderListener")
public class UserOrderListener implements ChannelAwareMessageListener {

    private static final Logger log = LoggerFactory.getLogger(UserOrderListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag = message.getMessageProperties().getDeliveryTag();

        byte[] body = message.getBody();
        String mobile = new String(body,"UTF-8");
        log.info("监听到：{}",mobile);

        //确认消费
        channel.basicAck(tag,true);

        //出现异常不消费
        //channel.basicReject(tag,false);
    }
}
