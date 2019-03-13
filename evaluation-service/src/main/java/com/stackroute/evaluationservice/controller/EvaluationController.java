package com.stackroute.evaluationservice.controller;

import com.stackroute.evaluationservice.domain.Notification;
import com.stackroute.evaluationservice.domain.Question;
import com.stackroute.evaluationservice.service.EvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class EvaluationController {
    private EvaluationService evaluationService;
    private RabbitTemplate rabbitTemplate;
    @Value("${jst.rabbitmq.exchange}")
    private String exchange;

    @Value("${jst.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    public EvaluationController(EvaluationService evaluationService, RabbitTemplate rabbitTemplate) {
        this.evaluationService = evaluationService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("result")
    public ResponseEntity<Question> getResultAfterEvaluation(@RequestParam String questionString) {
        ResponseEntity<Question> responseEntity;
        try {
            CompletableFuture<Question> questionCompletableFuture = evaluationService.searchInDb(questionString);
            CompletableFuture<List<Notification>> userListCompletableFuture = evaluationService.notifyUsersForTheQuestion(questionString);
            CompletableFuture.allOf(questionCompletableFuture, userListCompletableFuture);
            log.info("Recevied list of users and question document");
            Question question = questionCompletableFuture.get();
            log.info("Question document found: {}", question);
            List<Notification> eligibleUsers = userListCompletableFuture.get();
            log.info("Eligible users for notification: {}", eligibleUsers);
            if (question == null) {
                rabbitTemplate.convertAndSend(exchange, routingKey, eligibleUsers);
            }
            responseEntity = new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new Question(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }
}
