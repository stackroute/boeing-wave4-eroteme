package com.stackroute.automaticanswersearchservice.controller;

import com.stackroute.automaticanswersearchservice.Repository.AASRepo;
import com.stackroute.automaticanswersearchservice.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/question")
@Slf4j
public class AASController {

    private AASRepo aasRepo;


    private ScheduleService scheduleService;

    @Autowired
    public AASController(AASRepo aasRepo, ScheduleService scheduleService) {
        this.aasRepo = aasRepo;
        this.scheduleService = scheduleService;
    }

    //Overided method to save the data
    @GetMapping("/webresults")
    public ResponseEntity<?> saveData() {
        scheduleService.scheduleservice();
        ResponseEntity responseEntity = new ResponseEntity<>(aasRepo.findAll(), HttpStatus.OK);
        return responseEntity;
    }
}
