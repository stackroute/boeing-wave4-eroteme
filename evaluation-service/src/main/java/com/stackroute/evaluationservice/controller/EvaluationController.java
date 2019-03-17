package com.stackroute.evaluationservice.controller;

import com.stackroute.evaluationservice.domain.Notification;
import com.stackroute.evaluationservice.domain.Question;
import com.stackroute.evaluationservice.domain.UserNode;
import com.stackroute.evaluationservice.service.EvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@Slf4j
@CrossOrigin("*")
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
    public ResponseEntity<List<Question>> getResultAfterEvaluation(@RequestParam String questionString, @RequestParam String username) {
        ResponseEntity<List<Question>> responseEntity;
        try {
            CompletableFuture<Question> questionCompletableFuture = evaluationService.searchInDb(questionString);
            CompletableFuture<List<Question>> webResultCompletableFuture = evaluationService.searchInWeb(questionString);
            CompletableFuture<List<UserNode>> userListCompletableFuture = evaluationService.notifyUsersForTheQuestion(questionString);
            CompletableFuture.allOf(questionCompletableFuture, webResultCompletableFuture, userListCompletableFuture);

            log.info("Recevied list of eligible users, question document from db and web results");

            Question question = questionCompletableFuture.get();
            log.info("Question document found: {}", question);

            List<String> eligibleUsers = userListCompletableFuture.get()
                    .stream()
                    .filter(userNode -> !userNode.getUsername().equalsIgnoreCase(username))
                    .map(UserNode::getUsername)
                    .collect(Collectors.toList());
            log.info("Eligible users for notification: {}", eligibleUsers);

            List<Question> webResults = webResultCompletableFuture.get();
            log.info("Web results are {}", webResults);


            if (question == null || webResults == null || webResults.isEmpty()) {
                Notification notification = new Notification();
                notification.setEmails(eligibleUsers);
                notification.setQuestion(questionString);
                rabbitTemplate.convertAndSend(exchange, routingKey, notification);
                log.info("Sent notification: {}", notification);
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
            }
            webResults.add(question);
            responseEntity = new ResponseEntity<>(webResults, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }
}
