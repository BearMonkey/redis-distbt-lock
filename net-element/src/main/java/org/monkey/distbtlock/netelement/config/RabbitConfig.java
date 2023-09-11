package org.monkey.distbtlock.netelement.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Rabbit配置类
public class RabbitConfig {
    private final String EXCHANGE_NAME = "boot_topic_exchange";
    private final String QUEUE_NAME = "boot_queue";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.stream.username}")
    private String username;

    @Value("${spring.rabbitmq.stream.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;



    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
    /**
     * 使用 Jackson 转化器原因：
     * 1. 使数据在远程以json的形式保存
     * 2. 使不同的客户端接受相同类型的数据时，使数据能正常转换
     */
    @Bean(name = "converter")
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 表示自己建立连接，rabbitmq默认使用了guest访问，通过这种方式使用我们配置的用户
     *
     * @return ConnectionFactory
     */
    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host, port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        return cachingConnectionFactory;
    }

    // 创建交换机
    @Bean("bootExchange")
    public Exchange getExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)//交换机类型 ;参数为名字
                .durable(true)//是否持久化，true即存到磁盘,false只在内存上
                .build();
    }
    //创建队列
    @Bean("bootQueue")
    public Queue getMessageQueue() {
        return new Queue(QUEUE_NAME);
    }
    // 交换机绑定队列
    @Bean
    //@Qualifier注解,使用名称装配进行使用
    public Binding bindMessageQueue(
            @Qualifier("bootExchange") Exchange exchange,
            @Qualifier("bootQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("#.message.#")
                .noargs();
    }

}
