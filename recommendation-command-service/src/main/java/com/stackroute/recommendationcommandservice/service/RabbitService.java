package com.stackroute.recommendationcommandservice.service;

import com.stackroute.recommendationcommandservice.domain.QuestionDTO;
import com.stackroute.recommendationcommandservice.model.Question;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {
    @Autowired
    RecommendationCommandService recommendationCommandService;

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO msg) {
        System.out.println("Received Message: " + msg);
        System.out.println(msg.getAction());
//        System.out.println(msg.getQuestion());
//        System.out.println(msg.getUser());
//        Answer answer = msg.getAnswer().get(0);
        Question ques = new Question();
        ques.setQuestionId(msg.getQuestionId());
        ques.setQuestionString(msg.getQuestion());
        ques.setTimestamp(msg.getTimestamp());
        ques.setUpVote(msg.getUpvotes());
        ques.setDownVote(msg.getDownvotes());


        if (msg.getAction() == 1) {
            recommendationCommandService.saveQuestionToDb(ques);

        }
    }
}