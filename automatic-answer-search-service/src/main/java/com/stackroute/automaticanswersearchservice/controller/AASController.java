package com.stackroute.automaticanswersearchservice.controller;

import com.stackroute.StackOverflowAdaptor.service.APIservice;
import com.stackroute.automaticanswersearchservice.Repository.AASRepo;
import com.stackroute.automaticanswersearchservice.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/question")
@Slf4j
public class AASController {

    private AASRepo aasRepo;

    @Autowired
    private APIservice apiservice;

    public AASController(AASRepo aasRepo) {
        this.aasRepo = aasRepo;
    }

    //Overided method to save the data
    @GetMapping("/webresults")
    public ResponseEntity<?> saveData() {
        ResponseEntity responseEntity = new ResponseEntity<List<Question>>(aasRepo.findAll(), HttpStatus.OK);

        return responseEntity;
    }


}
