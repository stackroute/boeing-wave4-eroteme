package com.stackroute.recommendationcommandservice.service;

import com.stackroute.recommendationcommandservice.domain.QuestionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO msg) {
        System.out.println("Received Message: " + msg);
        System.out.println(msg.getAction());
//        System.out.println(msg.getQuestion());
//        System.out.println(msg.getUser());
//        Answer answer = msg.getAnswer().get(0);
    }
}