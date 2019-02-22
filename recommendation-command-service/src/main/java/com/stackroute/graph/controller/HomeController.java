package com.stackroute.graph.controller;


import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.Question;
import com.stackroute.graph.model.Topic;
import com.stackroute.graph.model.User;
import com.stackroute.graph.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@Slf4j
public class HomeController {
    private HomeService homeService;

    HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        ResponseEntity<String> responseEntity;
        try {
            homeService.saveUserToDb(user);
            responseEntity = new ResponseEntity<>("User saved sucessfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error occured while saving", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @PostMapping("/addtopic")
    public ResponseEntity<String> addTopic(@RequestBody Topic topic) {
        ResponseEntity<String> responseEntity;
        try {
            homeService.saveTopicToDb(topic);
            responseEntity = new ResponseEntity<>("User saved sucessfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error occured while saving", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    @PostMapping("/addquestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        ResponseEntity<String> responseEntity;
        try {
            homeService.saveQuestionToDb(question);
            responseEntity = new ResponseEntity<>("Question saved sucessfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error occured while saving", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @PostMapping("/addanswer")
    public ResponseEntity<String> addAnswer(@RequestBody Answer answer) {
        ResponseEntity<String> responseEntity;
        try {
            homeService.saveAnswerToDb(answer);
            responseEntity = new ResponseEntity<>("Answer saved sucessfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error occured while saving", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/getusers")
    public ResponseEntity<Collection<User>> getAllUsers() {
        ResponseEntity<Collection<User>> responseEntity;
        try {
            log.info("Fetching user nodes");
            responseEntity = new ResponseEntity(homeService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    @GetMapping("/getquestions")
    public ResponseEntity<Collection<Question>> getAllQuestions() {
        ResponseEntity<Collection<Question>> responseEntity;
        try {
            log.info("Fetching question nodes");
            responseEntity = new ResponseEntity(homeService.getQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    @GetMapping("/getanswers")
    public ResponseEntity<Collection<Answer>> getAllAnswers() {
        ResponseEntity<Collection<Answer>> responseEntity;
        try {
            log.info("Fetching answer nodes");
            responseEntity = new ResponseEntity(homeService.getAnswers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @DeleteMapping("/deleteanswer/{answerId}")
    public ResponseEntity<?> deleteallAnswer(@PathVariable("answerId") int answerId) {
        ResponseEntity<String> responseEntity;

        try {
            homeService.deleteAnswers(answerId);
            responseEntity = new ResponseEntity<>("Answer deleted sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error occured while deleting", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    @GetMapping("/user/{reputation}")
    public ResponseEntity<User> getUserByReputation(@PathVariable int reputation) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<>(homeService.getByUser(reputation), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    @GetMapping("/create/{userId}/{topicId}")
    public ResponseEntity<User> CreateRelationship(@PathVariable int userId, @PathVariable int topicId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.createRelationship(userId, topicId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/create1/{questionId}/{topicId}")
    public ResponseEntity<Question> CreateRelationshipone(@PathVariable int questionId, @PathVariable int topicId) {
        ResponseEntity<Question> responseEntity;
        try {

            responseEntity = new ResponseEntity<Question>(homeService.createRelationshipQT(questionId, topicId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new Question(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/create2/{userId}/{answerId}")
    public ResponseEntity<User> CreateRelationshiptwo(@PathVariable int userId, @PathVariable int answerId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.createRelationshipUA(userId, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/create3/{userId}/{questionId}")
    public ResponseEntity<User> CreateRelationshipthree(@PathVariable int userId, @PathVariable int questionId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.createRelationshipUQ(userId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/create4/{answerId}/{questionId}")
    public ResponseEntity<Answer> CreateRelationshipfour(@PathVariable int answerId, @PathVariable int questionId) {
        ResponseEntity<Answer> responseEntity;
        try {

            responseEntity = new ResponseEntity<Answer>(homeService.createRelationshipAQ(answerId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<Answer>(new Answer(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/create5/{userId}/{questionId}")
    public ResponseEntity<User> CreateRelationshipfive(@PathVariable int userId, @PathVariable int questionId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.useraskedquestion(userId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


}