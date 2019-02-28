package com.stackroute.nlp.controller;

import com.stackroute.nlp.model.NLP;
import com.stackroute.nlp.service.NlpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1")
public class NLPController {

    private NlpServiceImpl nlpService;

    @Autowired
    public NLPController(NlpServiceImpl nlpService)
    {
        this.nlpService=nlpService;
    }


}
