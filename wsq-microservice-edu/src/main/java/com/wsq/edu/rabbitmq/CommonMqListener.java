package com.wsq.edu.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsq.edu.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * 消费端的业务代码
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-9-12 17:13
 */
@Component
public class CommonMqListener {

    private static final Logger log = LoggerFactory.getLogger(CommonMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 监听消费用户日志
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message) {
        try {
            Teacher teacher = objectMapper.readValue(message, Teacher.class);
            log.info("监听消费用户日志 监听到消息： {} ", teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
