package com.stackroute.searchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;







import com.stackroute.searchservice.model.QuestionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
    public class RabbitService {

        @Autowired
        SearchServiceImpl searchService;

        @RabbitListener(queues = "${jsa.rabbitmq.queue}")
        public void receivedMessage(QuestionDTO msg) {
            System.out.println("Received Message: " + msg);
            try {
                searchService.questionOfPost(msg);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        @RabbitListener(queues = "${jsb.rabbitmq.queue}")
        public void receivedMessage(List<String> topic) {
            System.out.println("Received Message: " + topic);
            try {
                searchService.getQuestion(topic);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }



