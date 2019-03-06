package com.stackroute.recommendationcommandservice.service;

import com.stackroute.recommendationcommandservice.domain.AnswerDTO;
import com.stackroute.recommendationcommandservice.domain.QuestionDTO;
import com.stackroute.recommendationcommandservice.domain.UserProfileDTO;
import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static com.stackroute.recommendationcommandservice.service.Actions.*;

@Component
@Slf4j
public class RabbitService {
    @Autowired
    private RecommendationCommandServiceImpl recommendationCommandServiceImpl;


    /*this is the rabbit queue from Q A service*/
    @RabbitListener(queues = "${jse.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO questionDTO) {
        log.info("Received Message: " + questionDTO);
        System.out.println(questionDTO.getAction());

        /*
         * this questionDTO will have the properties need to set them and assign it to
         * our domain objects to call the methods
         * */

        Question ques = new Question();
        ques.setQuestionId(questionDTO.getQuestionId());
        List<String> name = questionDTO.getTopics();
        int questionId = ques.getQuestionId();
        ques.setQuestionString(questionDTO.getQuestion());
        ques.setTimestamp(questionDTO.getTimestamp());
        ques.setUpVote(questionDTO.getUpvotes());
        ques.setDownVote(questionDTO.getDownvotes());


        /*
         * when the action code sent by Q A service equals the below action i.e. POST_QUESTION
         * then call the below methods to save the question
         * and create relationship between question and topic
         * and create nodes in neo4j*/

        if (questionDTO.getAction() == POST_QUESTION) {
            recommendationCommandServiceImpl.saveQuestionToDb(ques);
            recommendationCommandServiceImpl.questionBelongsTopic(questionId, name);

        }

        /*
         * when the action code sent by Q A service equals the below action i.e. QUESTION_ANSWER
         * then call the below methods to save the answer
         * and create relationship between answer and question asked
         * and create nodes in neo4j
         * */

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


        /*when the action code sent by Q A service equals the below action i.e ANSWER_ACCEPT
         * then call the below methods to save the answer and set the accepted status
         * to true and update the node*/

        if (questionDTO.getAction() == ANSWER_ACCEPT) {
            System.out.println(questionDTO.getAnswer().size());
            int n = questionDTO.getAnswer().size();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO = questionDTO.getAnswer().get(n - 1);
            Answer answer = new Answer();
            answer.setAnswerString(answerDTO.getAnswer());
            answer.setAccepted(answerDTO.isAccepted());
            String answerString = answer.getAnswerString();
            User user = new User();
            user.setUserName(answerDTO.getUser().getEmail());
            answer.setUser(Collections.singletonList(user));

            recommendationCommandServiceImpl.saveAnswerToDb(answer);


        }
    }


    /*this is the queue from user profile registration service*/
    @RabbitListener(queues = "${jsf.rabbitmq.queue}")
    public void receivedMessage(UserProfileDTO user) {

        log.info("Received Message: " + user);

        /*
         * this userprofileDTO will have the properties need to set them and assign it to
         * our domain objects to call the methods
         * */

        user.setEmail(user.getEmail());
        user.setInterests(user.getInterests());
        User user1 = new User();
        user1.setUserName(user.getEmail());
        String userName = user1.getUserName();
        List<String> Name = user.getInterests();
        User user2 = user1;

//this method will save the user into db and create user node
        recommendationCommandServiceImpl.saveUserToDb(user2);
        log.info("user is created");


//this method creates relationship follows between user and topics
        recommendationCommandServiceImpl.userFollowsTopic(userName, Name);

    }
}