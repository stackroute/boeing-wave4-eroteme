package com.stackroute.automaticanswersearchservice.service;

import com.stackroute.StackOverflowAdaptor.domain.Items;
import com.stackroute.StackOverflowAdaptor.service.APIservice;
import com.stackroute.automaticanswersearchservice.Repository.AASRepo;
import com.stackroute.automaticanswersearchservice.model.Answer;
import com.stackroute.automaticanswersearchservice.model.Question;
import com.stackroute.automaticanswersearchservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScheduleService {

    @Autowired
    private AASRepo aasRepo;

    @Autowired
    private APIservice apiservice;

    public void scheduleservice() {

        log.info("Fetching results from web");
        try {
            List<Items> itemsList = apiservice.getData();
            log.info("List of Items" + itemsList);
            log.info("Itemlist size" + itemsList.size());
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
            log.info("Mapped data from StackExchange" + questions);
            aasRepo.save(questions);
            log.info("Saved web results in redis");
        } catch (Exception ex) {
            log.info("exception:" + ex);
        }
    }
}
