package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.service.RecommendationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class RecommendationController {
    @Value("trending-upvote-threshold")
    private int questionUpvoteThreshold;
    @Value("trending-number-of-answers-for-the-question")
    private int numberOfAnswersThreshold;

    private RecommendationServiceImpl recommendationServiceimpl;

    public RecommendationController(RecommendationServiceImpl recommendationServiceimpl) {
        this.recommendationServiceimpl = recommendationServiceimpl;
    }

    @GetMapping("/trending")
    public ResponseEntity<List<QuestionRequested>> getTrendingQuestionsForUser(@RequestParam String username, @RequestParam String topic) {
        ResponseEntity<List<QuestionRequested>> responseEntity;
        List<QuestionRequested> questionDocuments = new ArrayList<>();
        try {
            List<Question> questionNodes = recommendationServiceimpl.getTrendingQuestionsForUser(username, topic);
            questionNodes
                    .stream()
                    .filter(question -> question.getUpvote() >= questionUpvoteThreshold)
                    .collect(Collectors.toList())
                    .forEach(question -> questionDocuments.add(recommendationServiceimpl.getDocumentByQuestionId(question.getQuestionId())));
            List<QuestionRequested> trendingDocuments = questionDocuments
                    .stream()
                    .filter(questionRequested -> questionRequested.getAnswerDocuments().size() >= numberOfAnswersThreshold)
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(trendingDocuments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/unanswered/{userName}")
    public ResponseEntity<List<QuestionRequested>> getAllUnansweredQuestions(@PathVariable String username) {
        ResponseEntity<List<QuestionRequested>> responseEntity;
        List<QuestionRequested> questionRequested = new ArrayList<>();
        try {
            List<Question> unansweredQuestions = recommendationServiceimpl.getAllUnansweredQuestions(username);
            unansweredQuestions.forEach(question -> questionRequested.add(recommendationServiceimpl.getDocumentByQuestionId(question.getQuestionId())));
            responseEntity = new ResponseEntity<>(questionRequested, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @PostMapping(value = "/question")
    public ResponseEntity<String> addNewTrack(@RequestBody QuestionRequested questionRequested) {
        ResponseEntity<String> responseEntity;
        try {
            recommendationServiceimpl.insertIntoDb(questionRequested);
            responseEntity = new ResponseEntity<>("Question Added Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Occured While Adding Question", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}

