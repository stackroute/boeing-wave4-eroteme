package com.stackroute.nlp.controller;

import com.stackroute.nlp.exceptions.QuestionNotFoundException;
import com.stackroute.nlp.service.NlpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1")
public class NlpController
{
    NlpService nlpService;
    @Autowired
    public NlpController(NlpService nlpService) {
        this.nlpService = nlpService;
    }

    //getting question
    @GetMapping("{question}")
    @ResponseBody
    public String setquestion(@PathVariable String question) throws QuestionNotFoundException {
        return nlpService.setquestion(question);
    }
}

