package com.stackroute.recommendationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${jsf.rabbitmq.queue}")
    private String queueName;

    @Value("${jsf.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsf.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${jst.rabbitmq.queue}")
    private String queueName1;

    @Value("${jst.rabbitmq.exchange}")
    private String exchange1;

    @Value("${jst.rabbitmq.routingkey}")
    private String routingKey1;

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

    @Bean
    Queue queue1() {
        return new Queue(queueName1, false);
    }

    @Bean
    DirectExchange exchange1() {
        return new DirectExchange(exchange1);
    }

    @Bean
    Binding binding1(Queue queue, DirectExchange exchange1) {
        return BindingBuilder.bind(queue).to(exchange1).with(routingKey1);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
