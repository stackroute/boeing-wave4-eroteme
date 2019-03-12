package com.stackroute.editprofile.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //Recommendation Command Service
    @Value("${jfa.rabbitmq.queue}")
    private String queueNameRCS;

    @Value("${jfa.rabbitmq.exchange}")
    private String exchangeRCS;

    @Value("${jfa.rabbitmq.routingkey}")
    private String routingKeyRCS;

    //User-Profile service
    @Value("${jfb.rabbitmq.queue}")
    private String queueNameUP;

    @Value("${jfb.rabbitmq.exchange}")
    private String exchangeUP;

    @Value("${jfb.rabbitmq.routingkey}")
    private String routingKeyUP;

    //Question and Answer Service
    @Value("${jsc.rabbitmq.queue}")
    private String queueNameQA;

    @Value("${jsc.rabbitmq.exchange}")
    private String exchangeQA;

    @Value("${jsc.rabbitmq.routingkey}")
    private String routingKeyQA;

    //Login Service
    @Value("${jsd.rabbitmq.queue}")
    private String queueNameLS;

    @Value("${jsd.rabbitmq.exchange}")
    private String exchangeLS;

    @Value("${jsd.rabbitmq.routingkey}")
    private String routingKeyLS;

    //Search Service
    @Value("${jse.rabbitmq.queue}")
    private String queueNameSS;

    @Value("${jse.rabbitmq.exchange}")
    private String exchangeSS;

    @Value("${jse.rabbitmq.routingkey}")
    private String routingKeySS;


    //Recommendation Command service
    @Bean
    Queue queueRCS() {
        return new Queue(queueNameRCS, false);
    }

    @Bean
    DirectExchange exchangeRCS() {
        return new DirectExchange(exchangeRCS);
    }

    @Bean
    Binding bindingRCS(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyRCS);
    }

    //User-Profile Service
    @Bean
    Queue queueUP() {
        return new Queue(queueNameUP, false);
    }

    @Bean
    DirectExchange exchangeUP() {
        return new DirectExchange(exchangeUP);
    }

    @Bean
    Binding bindingUP(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyUP);
    }

    //Q&A service
    @Bean
    Queue queueQA() {
        return new Queue(queueNameQA, false);
    }

    @Bean
    DirectExchange exchangeQA() {
        return new DirectExchange(exchangeQA);
    }

    @Bean
    Binding bindingQA(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyQA);
    }

    //Login Service
    @Bean
    Queue queueLS() {
        return new Queue(queueNameLS, false);
    }

    @Bean
    DirectExchange exchangeLS() {
        return new DirectExchange(exchangeLS);
    }

    @Bean
    Binding bindingLS(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyLS);
    }

    //Search Service
    @Bean
    Queue queueSS() {
        return new Queue(queueNameSS, false);
    }

    @Bean
    DirectExchange exchangeSS() {
        return new DirectExchange(exchangeSS);
    }

    @Bean
    Binding bindingSS(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeySS);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
