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

    //Overided method to save the data
    @GetMapping("/data")
    public ResponseEntity<?> saveData() {
        ResponseEntity responseEntity;
        try {
            List<com.stackroute.StackOverflowAdaptor.domain.Items> itemsList = apiservice.getData();
            System.out.println("List of Items" + itemsList);
            System.out.println("Itemlist size" + itemsList.size());
            List<Question> questions = new ArrayList<>();
            //Mapping of data obtained from StackExchange to Question and Answer service model
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
            System.out.println("Mapped data from StackExchange" + questions);
            aasRepo.save(questions);
            responseEntity = new ResponseEntity<>("successfully saved", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Overided method to get all the mapped data
    @GetMapping("/all")
    public List<Question> getalldata() {
        return aasRepo.findAll();
    }
}
