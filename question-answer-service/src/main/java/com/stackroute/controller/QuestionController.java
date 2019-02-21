package com.stackroute.controller;

import com.stackroute.domain.Question;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping(value="api/v1")
public class QuestionController extends ResponseEntityExceptionHandler {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("ques")
    public ResponseEntity<?> saveUser(@RequestBody Question question)  {
        try {
            questionService.addQuestion(question);
            return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }catch (QuestionAlreadyExistsException e){
            System.out.println(e);
            return new ResponseEntity<String>("Successfully Created", HttpStatus.CONFLICT);
        }
    }
}
