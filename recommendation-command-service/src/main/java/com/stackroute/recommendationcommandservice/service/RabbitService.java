package com.stackroute.recommendationcommandservice.service;

import com.stackroute.recommendationcommandservice.domain.AnswerDTO;
import com.stackroute.recommendationcommandservice.domain.QuestionDTO;
import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.stackroute.recommendationcommandservice.service.Actions.POST_QUESTION;
import static com.stackroute.recommendationcommandservice.service.Actions.QUESTION_ANSWER;

@Component
@Slf4j
public class RabbitService {
    @Autowired
    private RecommendationCommandService recommendationCommandService;

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO questionDTO) {
        log.info("Received Message: " + questionDTO);
        System.out.println(questionDTO.getAction());

        Question ques = new Question();
        ques.setQuestionId(questionDTO.getQuestionId());
        ques.setQuestionString(questionDTO.getQuestion());
        ques.setTimestamp(questionDTO.getTimestamp());
        ques.setUpVote(questionDTO.getUpvotes());
        ques.setDownVote(questionDTO.getDownvotes());


//        Answer answer = new Answer();
//        List<AnswerDTO> answer1 = questionDTO.getAnswer();
//        answer1.forEach(answerDTO -> {
//        answer.setAnswerString(answerDTO.getAnswer());
//        User user = new User();
//        user.setUserName(answerDTO.getUser().getEmail());
//        answer.setUser(Collections.singletonList(user));


        if (questionDTO.getAction() == POST_QUESTION) {
            recommendationCommandService.saveQuestionToDb(ques);

        }


//        if (questionDTO.getAction() == 1) {
//            recommendationCommandService.saveQuestionToDb(ques);
//
//        }

//        questionDTO.getAnswer().forEach(answerDTO -> {
//            Answer answer = new Answer();
//            System.out.println("i got answer " + answerDTO.getAnswer());
//            answer.setAnswerString(answerDTO.getAnswer());
//            User user = new User();
//            user.setUserName(answerDTO.getUser().getEmail());
//            answer.setUser(Collections.singletonList(user));
        if (questionDTO.getAction() == QUESTION_ANSWER) {
            System.out.println(questionDTO.getAnswer().size());
            int n = questionDTO.getAnswer().size();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO = questionDTO.getAnswer().get(n - 1);
            Answer answer = new Answer();
            answer.setAnswerString(answerDTO.getAnswer());
            User user = new User();
            user.setUserName(answerDTO.getUser().getEmail());
            answer.setUser(Collections.singletonList(user));

            recommendationCommandService.saveAnswerToDb(answer);
        }


//            recommendationCommandService.saveAnswerToDb(answer);
//            int questionDTOAction = questionDTO.getAction();
//            if (questionDTO.getAction() == 2) {
//                recommendationCommandService.saveAnswerToDb(answer);
//            }
//            else if(questionDTO.getAction() == 7){
//                recommendationCommandService.userUpvoteQuestion(user.getUserName(),questionDTO.getQuestionId());
//            }

//        });









    }
}