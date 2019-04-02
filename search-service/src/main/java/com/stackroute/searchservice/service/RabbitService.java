package com.stackroute.searchservice.service;

import com.stackroute.searchservice.model.QuestionDTO;
import com.stackroute.searchservice.model.SearchData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
    public class  RabbitService {
    private List<String> topic;
    private String question;
        @Autowired
        SearchServiceImpl searchService;

    public String ques = null;

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
        public void receivedMessage(SearchData searchData) {
            System.out.println("Received Message: " + searchData);
            this.topic = searchData.getTopics();
            this.question = searchData.getQuestion();
        }

    public List<String> getTopic() {
        return topic;
    }

    public String getQuestion() {
        return question;
    }
}



