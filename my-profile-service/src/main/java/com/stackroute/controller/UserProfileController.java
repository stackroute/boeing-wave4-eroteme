package com.stackroute.controller;


import com.stackroute.domain.Question;
import com.stackroute.domain.UserCurrent;
import com.stackroute.domain.UserDTO;
import com.stackroute.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService=userProfileService;
    }


    @PutMapping("/question/{emailid}")
    public ResponseEntity<?> addQuestion(@PathVariable String emailid, @RequestBody Question question){
        System.out.println(question);
        return new ResponseEntity<String>(this.userProfileService.addQuestionToDB(emailid,question), HttpStatus.OK);
    }

    @PutMapping("/answer/{emailid}")
    public ResponseEntity<?> addAnswer(@PathVariable String emailid, @RequestBody Question question){
        return new ResponseEntity<String>(this.userProfileService.addAnswerToDb(emailid,question),HttpStatus.OK);
    }

    @GetMapping("/getall/{emailid}")
    public ResponseEntity<UserCurrent> getAllInfo(@PathVariable String emailid){
        return new ResponseEntity<UserCurrent>(this.userProfileService.returnAllInfoFromDb(emailid),HttpStatus.FOUND);
    }


   @RabbitListener(queues = "${jsi.rabbitmq.queue}")
    public void receivedMessage(UserDTO userDTO) {
        log.info("Received Message: " + userDTO);
        userProfileService.addnewUser(userDTO);
    }

    }
