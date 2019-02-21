package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.UserRepository;
import com.stackroute.recommendationservice.service.RecommendationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "recommendation/{username}", method = RequestMethod.GET)
@CrossOrigin("*")
public class RecommendationController {

    private UserRepository userRepository;

    @Autowired
    private RecommendationServiceImpl recommendationServiceimpl;

    @Autowired
    public RecommendationController(RecommendationServiceImpl recommendationServiceimpl) {
        this.recommendationServiceimpl = recommendationServiceimpl;
    }

    @GetMapping("/trending/{username}")
    public ResponseEntity<List<QuestionRequested>> getTrendingQuestionsForUser(@PathVariable String username) {
        ResponseEntity<List<QuestionRequested>> responseEntity;
        List<QuestionRequested> questionRequesteds = new ArrayList<>();
        try {
            List<Question> trendingQuestions = recommendationServiceimpl.getTrendingQuestionsForUser(username);
            trendingQuestions.forEach(question -> questionRequesteds.add(recommendationServiceimpl.getDocumentByQuestionId(question.getQuestionId())));
            responseEntity = new ResponseEntity<>(questionRequesteds, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("unanswered/{userName}")
    public ResponseEntity<List<QuestionRequested>> getAllUnansweredQuestions(@PathVariable String username) {
        ResponseEntity<List<QuestionRequested>> responseEntity;
        List<QuestionRequested> questionRequested = new ArrayList<>();
        try {
            List<Question> unansweredQuestions = recommendationServiceimpl.getAllUnansweredQuestions(username);
            unansweredQuestions.forEach(question -> questionRequested.add(recommendationServiceimpl.getDocumentByQuestionId(question.getQuestionId())));
            responseEntity = new ResponseEntity<>(questionRequested, HttpStatus.OK);
            System.out.println(questionRequested);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

//    @GetMapping("/domain/users")
//    public ResponseEntity<List<User>> getAllUsers(@PathVariable User users){
//        ResponseEntity<List<User>> responseEntity;
//        List<User> domainUsers = new ArrayList<>();
//        try {
//            domainUsers=userRepository.findAllUsers();
//            responseEntity=new ResponseEntity<>(domainUsers,HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
//        }
//        return responseEntity;
//
//    }
}

