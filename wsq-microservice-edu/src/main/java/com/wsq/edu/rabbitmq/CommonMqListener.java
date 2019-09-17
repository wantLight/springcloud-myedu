package com.wsq.edu.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsq.edu.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

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

    //直接消费模式
    @RabbitListener(queues = "${register.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMessage(@Payload Teacher record){
        try {
            log.debug("消费者监听交易记录信息： {} ",record);

            //TODO：表示已经到ttl了，却还没付款，则需要处理为失效
            if (Objects.equals(1,record.getCareer())){
//                record.setStatus(0);
//                record.setUpdateTime(new Date());
//                orderTradeRecordMapper.updateByPrimaryKeySelective(record);
            }
        }catch (Exception e){
            log.error("消息体解析 发生异常； ",e.fillInStackTrace());
        }
    }


}
