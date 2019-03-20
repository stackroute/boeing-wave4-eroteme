package com.stackroute.automaticanswersearchservice.controller;


import com.stackroute.StackOverflowAdaptor.service.APIservice;
import com.stackroute.automaticanswersearchservice.Repository.AASRepo;
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

    @GetMapping("/data")
    public ResponseEntity<?> getAllData() {
        ResponseEntity responseEntity;
        try {

            List<com.stackroute.StackOverflowAdaptor.domain.Items> itemsList = apiservice.getData();
            System.out.println("got the data" + itemsList);


            aasRepo.save(itemsList);
            responseEntity = new ResponseEntity<>("successfully saved", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @GetMapping("/all")
    public List<com.stackroute.StackOverflowAdaptor.domain.Items> all() {
        return aasRepo.findAll();
    }


}
