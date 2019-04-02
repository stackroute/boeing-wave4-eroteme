package com.stackroute.automaticanswersearchservice.controller;

import com.stackroute.automaticanswersearchservice.Repository.AASRepo;
import com.stackroute.automaticanswersearchservice.model.Question;
import com.stackroute.automaticanswersearchservice.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("post")
    public String save(@RequestBody List<Question> questions) {
        try {
            aasRepo.save(questions);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Success";

    }
}
