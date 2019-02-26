package com.stackroute.recommendationcommandservice.controller;


import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import com.stackroute.recommendationcommandservice.service.RecommendationCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@Slf4j
public class RecommendationController {
    private RecommendationCommandService homeService;
    ResponseEntity responseEntity;

    RecommendationController(RecommendationCommandService homeService) {
        this.homeService = homeService;
    }

    //method to add USER//
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user) {

        try {
            homeService.saveUserToDb(user);
            responseEntity = new ResponseEntity<String>("User saved sucessfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("Error occured while saving", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to add QUESTION//
    @PostMapping("/question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {

        try {
            homeService.saveQuestionToDb(question);
            responseEntity = new ResponseEntity<String>("Question saved sucessfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("Error occured while saving", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to add ANSWER//
    @PostMapping("/answer")
    public ResponseEntity<String> addAnswer(@RequestBody Answer answer) {
        try {
            homeService.saveAnswerToDb(answer);
            responseEntity = new ResponseEntity<String>("Answer saved sucessfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("Error occured while saving", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to get USERS//
    @GetMapping("/users")
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

    //method to get QUESTIONS//
    @GetMapping("/questions")
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


    //method to get ANSWERS//
    @GetMapping("/answers")
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


    //method to get USER using REPUTATION//
    @GetMapping("/user/{reputation}")
    public ResponseEntity<User> getUserByReputation(@PathVariable int reputation) {

        try {

            responseEntity = new ResponseEntity<User>(homeService.getByUser(reputation), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship FOLLOWS between user and topic//
    @GetMapping("/follows/{userName}/{name}")
    public ResponseEntity<User> CreateRelationshipfollows(@PathVariable String userName, @PathVariable String name) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.userfollowstopic(userName, name), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship QUESTION_OF between question and topic//
    @GetMapping("/belongs/{questionId}/{name}")
    public ResponseEntity<Question> CreateRelationshipbelongs(@PathVariable int questionId, @PathVariable String name) {
        try {

            responseEntity = new ResponseEntity<Question>(homeService.questionbelongstopic(questionId, name), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<Question>(new Question(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship ANSWERED between user and answer//
    @GetMapping("/answered/{userName}/{answerId}")
    public ResponseEntity<User> CreateRelationshipanswered(@PathVariable String userName, @PathVariable int answerId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.useransweredanswer(userName, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship VIEWED between user and question//
    @GetMapping("/viewed/{userName}/{questionId}")
    public ResponseEntity<User> CreateRelationshipviewed(@PathVariable String userName, @PathVariable int questionId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.userviewedquestion(userName, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship ANSWER_OF between answer and question//
    @GetMapping("/answerof/{answerId}/{questionId}")
    public ResponseEntity<Answer> CreateRelationshipanswerof(@PathVariable int answerId, @PathVariable int questionId) {
        try {

            responseEntity = new ResponseEntity<Answer>(homeService.answerisanswerofquestion(answerId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<Answer>(new Answer(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship ASKED between user and question//
    @GetMapping("/asked/{userName}/{questionId}")
    public ResponseEntity<User> CreateRelationshipasked(@PathVariable String userName, @PathVariable int questionId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.useraskedquestion(userName, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship ACCEPTED between user and answer//
    @GetMapping("/accepted/{userName}/{answerId}")
    public ResponseEntity<User> CreateRelationshipaccepted(@PathVariable String userName, @PathVariable int answerId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.useracceptedanswer(userName, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship UPVOTED between user and answer//
    @GetMapping("/upvoted/{userName}/{answerId}")
    public ResponseEntity<User> CreateRelationshipupvoted(@PathVariable String userName, @PathVariable int answerId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.userupvotedanswer(userName, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship DOWNVOTED between user and answer//
    @GetMapping("/downvoted/{userName}/{answerId}")
    public ResponseEntity<User> CreateRelationshipdownvoted(@PathVariable String userName, @PathVariable int answerId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.userdownvotedanswer(userName, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship UPVOTE between user and question//
    @GetMapping("/Upvoted/{userName}/{questionId}")
    public ResponseEntity<User> CreateRelationshipUpvoted(@PathVariable String userName, @PathVariable int questionId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.userupvotequestion(userName, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship DOWNVOTE between user and question//
    @GetMapping("/Downvoted/{userName}/{questionId}")
    public ResponseEntity<User> CreateRelationshipDownvoted(@PathVariable String userName, @PathVariable int questionId) {
        try {

            responseEntity = new ResponseEntity<User>(homeService.userdownvotequestion(userName, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

}