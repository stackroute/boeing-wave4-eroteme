package com.stackroute.evaluationservice.controller;

import com.stackroute.evaluationservice.domain.Notification;
import com.stackroute.evaluationservice.domain.Question;
import com.stackroute.evaluationservice.domain.QuestionDTO;
import com.stackroute.evaluationservice.domain.UserNode;
import com.stackroute.evaluationservice.service.EvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;
    @Value("${jst.rabbitmq.exchange}")
    private String exchange;

    @Value("${jst.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${questionAndAnswerUrl}")
    private String questionAndAnswerUrl;

    @Autowired
    public EvaluationController(EvaluationService evaluationService, RabbitTemplate rabbitTemplate, RestTemplate restTemplate) {
        this.evaluationService = evaluationService;
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    @PostMapping("result")
    public ResponseEntity<List<Question>> getResultAfterEvaluation(@RequestBody QuestionDTO questionDTO) {
        ResponseEntity<List<Question>> responseEntity;
        try {
            String questionString = questionDTO.getQuestion();
            CompletableFuture<Question> questionCompletableFuture = evaluationService.searchInDb(questionString);
            CompletableFuture<List<Question>> webResultCompletableFuture = evaluationService.searchInWeb();
            CompletableFuture.allOf(questionCompletableFuture, webResultCompletableFuture);

            log.info("Recevied list of eligible users, question document from db and web results");

            Question questionFromDb = questionCompletableFuture.get();
            log.info("Question document found: {}", questionFromDb);


            List<Question> webResults = webResultCompletableFuture.get();
            log.info("Web results are {}", webResults);


            if ((questionFromDb == null || questionFromDb.getQuestion() == null || questionFromDb.getQuestion().isEmpty())) {
                List<UserNode> userListCompletableFuture = evaluationService.notifyUsersForTheQuestion(questionDTO);
                List<String> eligibleUsers = userListCompletableFuture
                        .stream()
                        .peek(userNode -> log.info("User node is {}", userNode))
                        .filter(userNode -> !userNode.getUsername().equalsIgnoreCase(questionDTO.getUser().getUsername()))
                        .map(UserNode::getUsername)
                        .collect(Collectors.toList());
                log.info("Eligible users for notification: {}", eligibleUsers);
                Notification notification = new Notification();
                notification.setEmails(eligibleUsers);
                notification.setQuestion(questionString);
                rabbitTemplate.convertAndSend(exchange, routingKey, notification);
                log.info("Sent notification: {}", notification);
                return new ResponseEntity<>(webResults, HttpStatus.OK);
            }
            webResults.add(0, questionFromDb);

            responseEntity = new ResponseEntity<>(webResults, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }
}
