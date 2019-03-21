package com.stackroute.automaticanswersearchservice.controller;


import com.stackroute.StackOverflowAdaptor.service.APIservice;
import com.stackroute.automaticanswersearchservice.Repository.AASRepo;
import com.stackroute.automaticanswersearchservice.model.Answer;
import com.stackroute.automaticanswersearchservice.model.Question;
import com.stackroute.automaticanswersearchservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
            System.out.println("size" + itemsList.size());
            System.out.println("owner name" + itemsList.get(0).getOwner().getDisplay_name());
            List<Question> questions = new ArrayList<>();
            for (int i = 0; i < itemsList.size(); i++) {
                Question question = new Question();
                question.setQuestionId((int) itemsList.get(i).getQuestion_id());
                question.setQuestion(itemsList.get(i).getTitle());
                question.setTopics(itemsList.get(i).getTags());
                question.setUpvotes(itemsList.get(i).getScore());
                User user = new User();
                user.setFirstName(itemsList.get(i).getOwner().getDisplay_name());
                user.setImageUrl(itemsList.get(i).getOwner().getProfile_image());
                question.setUser(user);
                List<Answer> answers = new ArrayList<>();
                Answer answer = new Answer();
                answer.setAnswer(itemsList.get(i).getLink());
                answers.add(answer);

                question.setAnswer(answers);
                questions.add(question);
            }
            System.out.println("we get it" + questions);
            aasRepo.save(questions);
            System.out.println("we get it" + questions);

            responseEntity = new ResponseEntity<>("successfully saved", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/all")
    public List<Question> all() {
        return aasRepo.findAll();
    }
}
