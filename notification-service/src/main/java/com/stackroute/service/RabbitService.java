package com.stackroute.service;

import com.stackroute.domain.NotificationDTO;
import com.stackroute.domain.QuestionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {
    @Autowired
    Notifications notifications;

    //Rabbit Listener listening to Question and answer service
    @RabbitListener(queues = "${jsd.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO msg) {
        if(msg.getAction()==Actions.QUESTION_ANSWER){
            notifications.generateAnswerNotification(msg.getUser().getEmail(),msg.getQuestion());
        }
        if(msg.getAction()==Actions.ANSWER_ACCEPT){
            notifications.generateLikedNotification(msg.getAnswer().get(0).getUser().getEmail(),msg.getQuestion());
        }
    }

    //Rabbit Listener listening to Recommendation Query Service
    @RabbitListener(queues = "${jst.rabbitmq.queue}")
    public void receivedMessage1(NotificationDTO msg) {
        notifications.generateQuestionNotification(msg.getEmails(),msg.getQuestion());
    }

}
