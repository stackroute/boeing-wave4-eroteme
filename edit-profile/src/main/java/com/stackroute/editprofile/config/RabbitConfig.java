package com.stackroute.editprofile.config;

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
    //Recommendation Command Service
    @Value("${jfa.rabbitmq.queue}")
    private String queueRCS;

    @Value("${jfa.rabbitmq.exchange}")
    private String exchangeRCS;

    @Value("${jfa.rabbitmq.routingkey}")
    private String routingKeyRCS;

    //User-Profile service
    @Value("${jfb.rabbitmq.queue}")
    private String queueUP;

    @Value("${jfb.rabbitmq.exchange}")
    private String exchangeUP;

    @Value("${jfb.rabbitmq.routingkey}")
    private String routingKeyUP;

    //Question and Answer Service
    @Value("${jfc.rabbitmq.queue}")
    private String queueQA;

    @Value("${jfc.rabbitmq.exchange}")
    private String exchangeQA;

    @Value("${jfc.rabbitmq.routingkey}")
    private String routingKeyQA;

    //Login Service
    @Value("${jfd.rabbitmq.queue}")
    private String queueLS;

    @Value("${jfd.rabbitmq.exchange}")
    private String exchangeLS;

    @Value("${jfd.rabbitmq.routingkey}")
    private String routingKeyLS;

    //Search Service
    @Value("${jfe.rabbitmq.queue}")
    private String queueSS;

    @Value("${jfe.rabbitmq.exchange}")
    private String exchangeSS;

    @Value("${jfe.rabbitmq.routingkey}")
    private String routingKeySS;


    //Recommendation Command service
    @Bean
    Queue queueRCS() {
        return new Queue(queueRCS, false);
    }

    @Bean
    DirectExchange exchangeRCS() {
        return new DirectExchange(exchangeRCS);
    }

    @Bean
    Binding bindingRCS(Queue queueRCS, DirectExchange exchangeRCS) {
        return BindingBuilder.bind(queueRCS).to(exchangeRCS).with(routingKeyRCS);
    }

    //User-Profile Service
    @Bean
    Queue queueUP() {
        return new Queue(queueUP, false);
    }

    @Bean
    DirectExchange exchangeUP() {
        return new DirectExchange(exchangeUP);
    }

    @Bean
    Binding bindingUP(Queue queueUP, DirectExchange exchangeUP) {
        return BindingBuilder.bind(queueUP).to(exchangeUP).with(routingKeyUP);
    }

    //Q&A service
    @Bean
    Queue queueQA() {
        return new Queue(queueQA, false);
    }

    @Bean
    DirectExchange exchangeQA() {
        return new DirectExchange(exchangeQA);
    }

    @Bean
    Binding bindingQA(Queue queueQA, DirectExchange exchangeQA) {
        return BindingBuilder.bind(queueQA).to(exchangeQA).with(routingKeyQA);
    }

    //Login Service
    @Bean
    Queue queueLS() {
        return new Queue(queueLS, false);
    }

    @Bean
    DirectExchange exchangeLS() {
        return new DirectExchange(exchangeLS);
    }

    @Bean
    Binding bindingLS(Queue queueLS, DirectExchange exchangeLS) {
        return BindingBuilder.bind(queueLS).to(exchangeLS).with(routingKeyLS);
    }

    //Search Service
    @Bean
    Queue queueSS() {
        return new Queue(queueSS, false);
    }

    @Bean
    DirectExchange exchangeSS() {
        return new DirectExchange(exchangeSS);
    }

    @Bean
    Binding bindingSS(Queue queueSS, DirectExchange exchangeSS) {
        return BindingBuilder.bind(queueSS).to(exchangeSS).with(routingKeySS);
    }


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
