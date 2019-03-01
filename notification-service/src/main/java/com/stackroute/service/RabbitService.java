package com.stackroute.service;

import com.stackroute.domain.QuestionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {
    @Autowired
    Notifications notifications;

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO msg) {

        System.out.println("Received Message: " + msg);
        if(msg.getAction()==2){
            notifications.generateAnswerNotification(msg.getUser().getEmail(),msg.getQuestion());
        }
        if(msg.getAction()==14){
            notifications.generateLikedNotification(msg.getAnswer().get(0).getUser().getEmail(),msg.getQuestion());
        }
    }

}