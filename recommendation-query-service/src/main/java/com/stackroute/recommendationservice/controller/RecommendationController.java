package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.domain.*;
import com.stackroute.recommendationservice.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@CrossOrigin("*")
@PropertySource(value = "classpath:trending-criteria.yml")
public class RecommendationController {
    @Value("${trending-timestampThreshold}")
    private long timestampThreshold;
    @Value("${trending-upvote-threshold}")
    private int questionUpvoteThreshold;
    @Value("${trending-number-of-answers-for-the-question}")
    private int numberOfAnswersThreshold;
    @Value("${reputation-to-answer-the-question}")
    private int reputationNeeded;
    private RabbitTemplate rabbitTemplate;
    @Value("${jst.rabbitmq.exchange}")
    private String exchange;

    @Value("${jst.rabbitmq.routingkey}")
    private String routingKey;


    private RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.recommendationService = recommendationService;
    }

    /**
     * @param username Username of the registered userNode3
     * @return Trending questions for the logged in userNode3
     */
    @GetMapping("/member/trending")
    public ResponseEntity<List<Question>> getTrendingQuestionsForRegisteredUser(@RequestParam String username) {
        ResponseEntity<List<Question>> responseEntity;
        List<Question> questionDocuments = new ArrayList<>();
        try {
            List<Question> trendingDocuments = recommendationService.getTrendingQuestionsForRegisteredUser(username)
                    .stream()
                    .peek(question -> log.info("Question node is {}", question))
                    .map(question -> recommendationService.getDocumentByQuestionId(question.getQuestionId()))
                    .peek(questionRequested -> log.info("Question document is {}", questionRequested))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(trendingDocuments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @return Trending questions for the guest userNode3
     */
    @GetMapping("/guest/trending")
    public ResponseEntity<List<Question>> getTrendingQuestionsForGuestUser() {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> questionDocuments = recommendationService.getTrendingQuestionsForGuestUser();
            responseEntity = new ResponseEntity<>(questionDocuments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @param question posted question
     * @param action Action enum
     * @return List of users who will be notified
     */

    @GetMapping("/member/notify")
    @ResponseBody
    public List<UserNode> getAllUsersToBeNotified(@RequestParam String question, @RequestParam String action) {

        List<UserNode> userNodes = new ArrayList<>();

        if (action.equalsIgnoreCase(Actions.POST_QUESTION.name())) {
            try {
                userNodes = recommendationService.getAllUsersRelatedToQuestion(question);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userNodes;
    }

    /**
     * @param username Username of the registered userNode3
     * @return List of unanswered questions for the logged in userNode3
     */
    @GetMapping("/member/unanswered/{username}")
    public ResponseEntity<List<Question>> getAllUnansweredQuestionsForRegisteredUser(@PathVariable String username) {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> unansweredQuestions = recommendationService.getAllUnansweredQuestionsForRegisteredUser(username)
                    .stream()
                    .map(question -> recommendationService.getDocumentByQuestionId(question.getQuestionId()))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(unansweredQuestions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @return List of unanswered questions for guest userNode3
     */
    @GetMapping("/guest/unanswered")
    public ResponseEntity<List<Question>> getAllUnansweredQuestionsForGuestUser() {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> question = recommendationService.getAllUnansweredQuestionsForGuestUser()
                    .stream()
                    .sorted(Comparator.comparing(Question::getTimestamp))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @param username Username of the loggedin userNode3
     * @return List of Accepted answers of the domain which userNode3 follows
     */
    @GetMapping("/member/acceptedanswers")
    public ResponseEntity<List<Question>> getAllAcceptedAnswersOfDomain(@RequestParam String username) {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> acceptedAnswers = recommendationService.getAllAcceptedAnswersOfDomain(username)
                    .stream()
                    .map(question -> recommendationService.getDocumentByQuestionId(question.getQuestionId()))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(acceptedAnswers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    /**
     * @return List of accepted answers to guest users
     */
    @GetMapping("/guest/acceptedanswers")
    public ResponseEntity<List<Question>> getAcceptedAnswersForGuest() {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> question = recommendationService.getAllAcceptedAnswersForGuestUser()
                    .stream()
                    .sorted(Comparator.comparing(Question::getTimestamp))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RabbitListener(queues = "${jsf.rabbitmq.queue}")
    public void sendNotificationData(QuestionDTO questionDTO) throws InterruptedException {
        log.info("Received {}", questionDTO);
        Notification notification;
        Thread.sleep(2000);
        List<UserNode> allUsersRelatedToQuestion = recommendationService.getAllUsersRelatedToQuestion(questionDTO.getQuestion());

        log.info("Eligible users for notification are: {}", allUsersRelatedToQuestion);
        if (allUsersRelatedToQuestion == null || allUsersRelatedToQuestion.isEmpty()) {
            log.warn("No Users found following the topic");
            notification = Notification.builder().emails(Collections.emptyList()).question(questionDTO.getQuestion()).build();
        } else {

            List<String> emails = allUsersRelatedToQuestion
                    .stream()
                    .filter(userNode -> !userNode.getUsername().equalsIgnoreCase(questionDTO.getUser().getEmail()))
                    .peek(userNode -> log.info("User is {}", userNode))
                    .map(UserNode::getUsername)
                    .collect(Collectors.toList());
            notification = Notification.builder().emails(emails).question(questionDTO.getQuestion()).build();
        }
        log.info("Notification sent: {}", notification);
        rabbitTemplate.convertAndSend(exchange, routingKey, notification);
    }
}

