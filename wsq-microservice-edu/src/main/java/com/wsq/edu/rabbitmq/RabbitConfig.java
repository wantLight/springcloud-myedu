package com.wsq.edu.rabbitmq;

/**
 * RabbitMQ 作为目前应用相当广泛的消息中间件，在企业级应用、微服务应用中充当着重要的角色。
 * 特别是在一些典型的应用场景以及业务模块中具有重要的作用，
 * 比如业务服务模块解耦、异步通信、高并发限流、超时业务、数据延迟处理等。
 *
 *
 * 创建RabbitMQ的配置类RabbitConfig，
 * 用来配置队列、交换器、路由等高级信息。
 *
 *
 * 生产者：发送消息的程序
 * 消费者：监听接收消费消息的程序
 * 消息：一串二进制数据流
 * 队列：消息的暂存区/存储区
 * 交换机：消息的中转站，用于接收分发消息。其中有 fanout、direct、topic、headers 四种
 * 路由：相当于密钥/第三者，与交换机绑定即可路由消息到指定的队列！
 *
 *
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-9-12 11:15
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;


//@Configuration
public class RabbitConfig {


    private static final Logger log= LoggerFactory.getLogger(RabbitConfig.class);

    public static final String MIAOSHA_QUEUE = "hello";

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    @Autowired
    private UserOrderListener userOrderListener;


    /**
     * 延时队列”
     * “生产者生产消息，消息进入队列之后，并不会立即被指定的消费者所消费，而是会延时一段指定的时间ttl，最终才被消费者消费
     */
    @Bean(name = "registerDelayQueue")
    public Queue registerDelayQueue(){
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange",env.getProperty("register.exchange.name"));
        params.put("x-dead-letter-routing-key","all");
        return new Queue(env.getProperty("register.delay.queue.name"), true,false,false,params);
    }

    @Bean
    public DirectExchange registerDelayExchange(){
        return new DirectExchange(env.getProperty("register.delay.exchange.name"));
    }

    @Bean
    public Binding registerDelayBinding(){
        return BindingBuilder.bind(registerDelayQueue()).to(registerDelayExchange()).with("");
    }

    /**延迟队列配置**/

    /**指标消费队列配置**/

    @Bean
    public TopicExchange registerTopicExchange(){
        return new TopicExchange(env.getProperty("register.exchange.name"));
    }

    @Bean
    public Binding registerBinding(){
        return BindingBuilder.bind(registerQueue()).to(registerTopicExchange()).with("all");
    }

    @Bean(name = "registerQueue")
    public Queue registerQueue(){
        return new Queue(env.getProperty("register.queue.name"),true);
    }






    /**
     * 在 RabbitMQConfig 中配置 确认消费机制 以及并发量的配置
     */
    @Bean(name = "userOrderQueue")
    public Queue userOrderQueue(){
        return new Queue(env.getProperty("user.order.queue.name"),true);
    }

    @Bean
    public TopicExchange userOrderExchange(){
        return new TopicExchange(env.getProperty("user.order.exchange.name"),true,false);
    }

    @Bean
    public Binding userOrderBinding(){
        return BindingBuilder.bind(userOrderQueue()).to(userOrderExchange()).with(env.getProperty("user.order.routing.key.name"));
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainerUserOrder(@Qualifier("userOrderQueue") Queue userOrderQueue){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        //并发配置
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",Integer.class));
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",Integer.class));
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",Integer.class));

        //消息确认机制
        container.setQueues(userOrderQueue);
        //todo 确认机制需要 listener 实现 ChannelAwareMessageListener 接口，并重写其中的确认消费逻辑。在这里我们将用 “手动确认” 的机制来实战用户商城抢单场景。
        container.setMessageListener(userOrderListener);
        // 消息确认处理机制有三种：Auto - 自动、Manual - 手动、None - 无需确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return container;
    }


    /**
     * 消息模型：DirectExchange+RoutingKey 消息模型
     * RabbitMQ 的 DirectExchange+RoutingKey
     * 消息模型也实现“用户登录成功记录日志”的场景
     */
    @Bean(name = "logUserQueue")
    public Queue logUserQueue(){
        return new Queue(env.getProperty("log.user.queue.name"),true);
    }

    @Bean
    public DirectExchange logUserExchange(){
        return new DirectExchange(env.getProperty("log.user.exchange.name"),true,false);
    }

    @Bean
    public Binding logUserBinding(){
        return BindingBuilder.bind(logUserQueue()).to(logUserExchange()).with(env.getProperty("log.user.routing.key.name"));
    }




    /**
     * 配置队列名
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(MIAOSHA_QUEUE);
    }


    /**
     * RabbitMQ监听器listener 的容器工厂
     *
     * 单一消费者
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * RabbitMQ监听器listener 的容器工厂
     *
     * 多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",int.class));
        return factory;
    }

    /**
     * 充当消息的发送组件
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }

}

