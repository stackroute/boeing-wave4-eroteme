package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;







import com.stackroute.searchservice.model.QuestionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
    public class  RabbitService {
 public List<String> topic;
        @Autowired
        SearchServiceImpl searchService;

    public List<Question> ques=null;

        @RabbitListener(queues = "${jsa.rabbitmq.queue}")
        public void receivedMessage(QuestionDTO msg) {

            System.out.println("Received Message: " + msg);
            try {
                ques=searchService.questionOfPost(msg);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        @RabbitListener(queues = "${jsb.rabbitmq.queue}")
        public void receivedMessage(List<String> topic) {
            System.out.println("Received Message: " + topic);
            this.topic=topic;
            System.out.println(this.topic);

        }
    }



