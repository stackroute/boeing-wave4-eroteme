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
    private RecommendationCommandServiceImpl recommendationCommandServiceImpl;

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO questionDTO) {
        log.info("Received Message: " + questionDTO);
        System.out.println(questionDTO.getAction());

        Question ques = new Question();
        ques.setQuestionId(questionDTO.getQuestionId());
        int questionId = ques.getQuestionId();
        ques.setQuestionString(questionDTO.getQuestion());
        ques.setTimestamp(questionDTO.getTimestamp());
        ques.setUpVote(questionDTO.getUpvotes());
        ques.setDownVote(questionDTO.getDownvotes());


        if (questionDTO.getAction() == POST_QUESTION) {
            recommendationCommandServiceImpl.saveQuestionToDb(ques);

        }

        if (questionDTO.getAction() == QUESTION_ANSWER) {
            System.out.println(questionDTO.getAnswer().size());
            int n = questionDTO.getAnswer().size();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO = questionDTO.getAnswer().get(n - 1);
            Answer answer = new Answer();
            answer.setAnswerString(answerDTO.getAnswer());
            String answerString = answer.getAnswerString();
            User user = new User();
            user.setUserName(answerDTO.getUser().getEmail());
            answer.setUser(Collections.singletonList(user));

            recommendationCommandServiceImpl.saveAnswerToDb(answer);
            recommendationCommandServiceImpl.answerIsAnswerOfQuestion(answerString, questionId);
        }

//        if (questionDTO.getAction() == ANSWER_ACCEPT) {
//            System.out.println(questionDTO.getAnswer().size());
//            int n = questionDTO.getAnswer().size();
//            AnswerDTO answerDTO = new AnswerDTO();
//            answerDTO = questionDTO.getAnswer().get(n - 1);
//            Answer answer = new Answer();
//            answer.setAnswerString(answerDTO.getAnswer());
//            String answerString = answer.getAnswerString();
//            User user = new User();
//            user.setUserName(questionDTO.getUser().getEmail());
//            String userName = user.getUserName();
//            System.out.println(userName);
//            System.out.println(answerString);
//            answer.setUser(Collections.singletonList(user));
//
//            recommendationCommandServiceImpl.userAcceptedAnswer(userName,answerString);
//        }














    }
}