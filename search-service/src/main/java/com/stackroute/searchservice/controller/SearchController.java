package com.stackroute.searchservice.controller;


import com.stackroute.searchservice.domain.Question;
import com.stackroute.searchservice.service.RabbitService;
import com.stackroute.searchservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin(origins = "*")

public class SearchController {

    @Autowired
    private RabbitService rabbitService;
    private SearchService searchService;


    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //Displaying saved topic information
//    @GetMapping("topics")
//    public ResponseEntity<?> getAllTopics() {
//        ResponseEntity responseEntity;
//        try {
//            responseEntity = new ResponseEntity<List<Topic>>(searchService.getAllTopics(), HttpStatus.OK);
//        } catch (Exception ex) {
//            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
//        }
//        return responseEntity;
//    }
//
//    @GetMapping("topic")
//    public ResponseEntity<?> getQuestion() {
//
//        ResponseEntity responseEntity;
//        try {
//
//            responseEntity = new ResponseEntity<List<Topic>>(searchService.getQuestion(rabbitService.getTopic()), HttpStatus.OK);
//
//        } catch (Exception ex) {
//            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
//        return responseEntity;
//    }


    @GetMapping(path = "relevant")
    public ResponseEntity<?> getQuestionInside() {
        ResponseEntity responseEntity;
        try {

            return new ResponseEntity<List<Question>>(searchService.getQuestionInside(rabbitService.getTopic(), rabbitService.getQuestion()), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}


