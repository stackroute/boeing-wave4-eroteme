package com.stackroute.configuration;

import com.stackroute.domain.Notifications;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
//Configuration class for rabbitmq
public class RabbitmqConfig {

    //declaring queue,exchange and binding key for question and answer service
    @Value("${jsd.rabbitmq.queue}")
    private String queueName;

    @Value("${jsd.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsd.rabbitmq.routingkey}")
    private String routingKey;

    //declaring queue,exchange and binding key for recommendation query service
    @Value("${jst.rabbitmq.queue}")
    private String queueName1;

    @Value("${jst.rabbitmq.exchange}")
    private String exchange1;

    @Value("${jst.rabbitmq.routingkey}")
    private String routingKey1;

    //Redis Template for using redis database
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, Notifications> redisTemplate() {
        RedisTemplate<String,Notifications> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    //Queue,Exchange and binding key for question and answer service
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    //Queue,exchange and binding key for recommendation query service
    @Bean
    Queue queue1() {
        return new Queue(queueName1, false);
    }

    @Bean
    DirectExchange exchange1() {
        return new DirectExchange(exchange1);
    }

    @Bean
    Binding binding1(Queue queue1, DirectExchange exchange1) {
        return BindingBuilder.bind(queue1).to(exchange1).with(routingKey1);
    }

    //message converter used for deserialization of DTO
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //RabbitTemplate being used for message transfer
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
