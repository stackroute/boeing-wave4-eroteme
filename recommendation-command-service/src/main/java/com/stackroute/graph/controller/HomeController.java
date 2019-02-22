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

    //method to add USER//
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

    //method to add TOPIC//
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

    //method to add QUESTION//
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

    //method to add ANSWER//
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


    //method to get USERS//
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

    //method to get QUESTIONS//
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


    //method to get ANSWERS//
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

    //method to get TOPIC//
    @GetMapping("/gettopics")
    public ResponseEntity<Collection<Topic>> getAllTopics() {
        ResponseEntity<Collection<Topic>> responseEntity;
        try {
            log.info("Fetching topic nodes");
            responseEntity = new ResponseEntity(homeService.getTopics(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to delete ANSWER using ANSWERID//
    @DeleteMapping("/deleteanswer")
    public ResponseEntity<?> deleteallAnswer(@RequestParam long answerId) {
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


    //method to get USER using REPUTATION//
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

    //method to create relationship FOLLOWS between user and topic//
    @GetMapping("/follows/{userId}/{name}")
    public ResponseEntity<User> CreateRelationship(@PathVariable int userId, @PathVariable String name) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.userfollowstopic(userId, name), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship BELONGS between question and topic//
    @GetMapping("/belongs/{questionId}/{topicId}")
    public ResponseEntity<Question> CreateRelationshipone(@PathVariable int questionId, @PathVariable int topicId) {
        ResponseEntity<Question> responseEntity;
        try {

            responseEntity = new ResponseEntity<Question>(homeService.questionbelongstopic(questionId, topicId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new Question(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship ANSWERED between user and answer//
    @GetMapping("/answered/{userId}/{answerId}")
    public ResponseEntity<User> CreateRelationshiptwo(@PathVariable int userId, @PathVariable int answerId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.useransweredanswer(userId, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship VIEWED between user and question//
    @GetMapping("/viewed/{userId}/{questionId}")
    public ResponseEntity<User> CreateRelationshipthree(@PathVariable int userId, @PathVariable int questionId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.userviewedquestion(userId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship ANSWER_OF between answer and question//
    @GetMapping("/answerof/{answerId}/{questionId}")
    public ResponseEntity<Answer> CreateRelationshipfour(@PathVariable int answerId, @PathVariable int questionId) {
        ResponseEntity<Answer> responseEntity;
        try {

            responseEntity = new ResponseEntity<Answer>(homeService.answerisanswerofquestion(answerId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<Answer>(new Answer(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship ASKED between user and question//
    @GetMapping("/asked/{userId}/{questionId}")
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

    //method to create relationship ACCEPTED between user and answer//
    @GetMapping("/accepted/{userId}/{answerId}")
    public ResponseEntity<User> CreateRelationshipsix(@PathVariable int userId, @PathVariable int answerId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.useracceptedanswer(userId, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


    //method to create relationship UPVOTED between user and answer//
    @GetMapping("/upvoted/{userId}/{answerId}")
    public ResponseEntity<User> CreateRelationshipseven(@PathVariable int userId, @PathVariable int answerId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.userupvotedanswer(userId, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship DOWNVOTED between user and answer//
    @GetMapping("/downvoted/{userId}/{answerId}")
    public ResponseEntity<User> CreateRelationshipeight(@PathVariable int userId, @PathVariable int answerId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.userdownvotedanswer(userId, answerId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship UPVOTE between user and question//
    @GetMapping("/upvote/{userId}/{questionId}")
    public ResponseEntity<User> CreateRelationshipnine(@PathVariable int userId, @PathVariable int questionId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.userupvotequestion(userId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    //method to create relationship DOWNVOTE between user and question//
    @GetMapping("/downvote/{userId}/{questionId}")
    public ResponseEntity<User> CreateRelationshipten(@PathVariable int userId, @PathVariable int questionId) {
        ResponseEntity<User> responseEntity;
        try {

            responseEntity = new ResponseEntity<User>(homeService.userdownvotequestion(userId, questionId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }


//    @GetMapping("/subtopicof/{topicId}/{topicId}")
//    public ResponseEntity<User> CreateRelationshipeleven(@PathVariable int topicId, @PathVariable String topicname) {
//        ResponseEntity<User> responseEntity;
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userfollowstopic(topicname,topicId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<>(new User    (), HttpStatus.BAD_GATEWAY);
//        }
//        return responseEntity;
//    }
}