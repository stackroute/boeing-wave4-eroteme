package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@CrossOrigin("*")
@PropertySource(value = "classpath:trending-criteria.yml")
public class RecommendationController {
    @Value("${trending-upvote-threshold}")
    private int questionUpvoteThreshold;
    @Value("${trending-number-of-answers-for-the-question}")
    private int numberOfAnswersThreshold;
    @Value("${reputation-to-answer-the-question}")
    private int reputationNeeded;

    private RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * @param username Username of the registered user
     * @return Trending questions for the user
     */
    @GetMapping("/trending")
    public ResponseEntity<List<QuestionRequested>> getTrendingQuestionsForUser(@RequestParam String username) {
        ResponseEntity<List<QuestionRequested>> responseEntity;
        List<QuestionRequested> questionDocuments = new ArrayList<>();
        try {
            List<Question> questionNodes = recommendationService.getTrendingQuestionsForUser(username);
            questionNodes
                    .stream()
                    .peek(question -> log.info("Question nodes is {}", question))
                    .filter(question -> question.getUpvote() >= questionUpvoteThreshold)
                    .collect(Collectors.toList())
                    .forEach(question -> questionDocuments.add(recommendationService.getDocumentByQuestionId(question.getQuestionId())));
            List<QuestionRequested> trendingDocuments = questionDocuments
                    .stream()
                    .filter(questionRequested -> questionRequested.getAnswerDocuments().size() >= numberOfAnswersThreshold)
                    .peek(questionRequested -> log.info(" Question document is {}", questionRequested))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(trendingDocuments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/notifyUsers")
    public ResponseEntity<List<User>> getAllUsersOfTopic(@RequestBody Question question) {
        ResponseEntity<List<User>> responseEntity;
        List<User> users;
        try {
            users = recommendationService.getAllUsersRelatedToQuestion(question.getQuestionId());
            users = users.stream().filter(user -> user.getReputation() >= reputationNeeded).collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/unanswered/{username}")
    public ResponseEntity<List<QuestionRequested>> getAllUnansweredQuestions(@PathVariable String username) {
        ResponseEntity<List<QuestionRequested>> responseEntity;
        List<QuestionRequested> questionRequested = new ArrayList<>();
        try {
            List<Question> unansweredQuestions = recommendationService.getAllUnansweredQuestions(username);
            unansweredQuestions.forEach(question -> questionRequested.add(recommendationService.getDocumentByQuestionId(question.getQuestionId())));
            responseEntity = new ResponseEntity<>(questionRequested, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    // This method is added only for testing purpose
    @PostMapping(value = "/question")
    public ResponseEntity<String> addNewTrack(@RequestBody QuestionRequested questionRequested) {
        ResponseEntity<String> responseEntity;
        try {
            recommendationService.insertIntoDb(questionRequested);
            responseEntity = new ResponseEntity<>("Question Added Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Occured While Adding Question", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }



}

