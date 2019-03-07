package com.stackroute.recommendationcommandservice.service;

import com.stackroute.recommendationcommandservice.domain.AnswerDTO;
import com.stackroute.recommendationcommandservice.domain.QuestionDTO;
import com.stackroute.recommendationcommandservice.domain.UserProfileDTO;
import com.stackroute.recommendationcommandservice.model.AnswerNode;
import com.stackroute.recommendationcommandservice.model.QuestionNode;
import com.stackroute.recommendationcommandservice.model.UserNode;
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

        QuestionNode ques = new QuestionNode();
        ques.setQuestionId(questionDTO.getQuestionId());
        List<String> name = questionDTO.getTopics();
        int questionId = ques.getQuestionId();
        ques.setQuestion(questionDTO.getQuestion());
        ques.setTimestamp(questionDTO.getTimestamp());
        ques.setUpvote(questionDTO.getUpvotes());
        ques.setDownvote(questionDTO.getDownvotes());


        /*
         * when the action code sent by Q A service equals the below action i.e. POST_QUESTION
         * then call the below methods to save the question
         * and create relationship between question and topic
         * and create nodes in neo4j*/

        if (questionDTO.getAction() == POST_QUESTION) {
            recommendationCommandServiceImpl.saveQuestionToDb(ques);
            log.info("question is posted and saved");
            recommendationCommandServiceImpl.questionBelongsTopic(questionId, name);
            log.info("question belongs to this topic relationship is created");

        }

        /*
         * when the action code sent by Q A service equals the below action i.e. QUESTION_ANSWER
         * then call the below methods to save the answerNode
         * and create relationship between answerNode and question asked
         * and create nodes in neo4j
         * */

        if (questionDTO.getAction() == QUESTION_ANSWER) {
            System.out.println(questionDTO.getAnswer().size());
            int n = questionDTO.getAnswer().size();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO = questionDTO.getAnswer().get(n - 1);
            AnswerNode answerNode = new AnswerNode();
            answerNode.setAnswer(answerDTO.getAnswer());
            String answerString = answerNode.getAnswer();
            UserNode userNode = new UserNode();
            userNode.setUsername(answerDTO.getUser().getEmail());
            answerNode.setUserNode(Collections.singletonList(userNode));

            recommendationCommandServiceImpl.saveAnswerToDb(answerNode);
            log.info("answerNode is posted and saved");
            recommendationCommandServiceImpl.answerIsAnswerOfQuestion(answerString, questionId);
            log.info("relationship answerNode of is created");
        }


        /*when the action code sent by Q A service equals the below action i.e ANSWER_ACCEPT
         * then call the below methods to save the answerNode and set the accepted status
         * to true and update the node*/

        if (questionDTO.getAction() == ANSWER_ACCEPT) {
            System.out.println(questionDTO.getAnswer().size());
            int n = questionDTO.getAnswer().size();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO = questionDTO.getAnswer().get(n - 1);
            AnswerNode answerNode = new AnswerNode();
            answerNode.setAnswer(answerDTO.getAnswer());
            answerNode.setAccepted(answerDTO.isAccepted());
            String answerString = answerNode.getAnswer();
            UserNode userNode = new UserNode();
            userNode.setUsername(answerDTO.getUser().getEmail());
            answerNode.setUserNode(Collections.singletonList(userNode));

            recommendationCommandServiceImpl.saveAnswerToDb(answerNode);
            log.info("answerNode is set to accepted");


        }
    }


    /*this is the queue from userNode3 profile registration service*/
    @RabbitListener(queues = "${jsf.rabbitmq.queue}")
    public void receivedMessage(UserProfileDTO user) {

        log.info("Received Message: " + user);

        /*
         * this userprofileDTO will have the properties need to set them and assign it to
         * our domain objects to call the methods
         * */

        user.setEmail(user.getEmail());
        user.setInterests(user.getInterests());
        UserNode userNode1 = new UserNode();
        userNode1.setUsername(user.getEmail());
        String userName = userNode1.getUsername();
        List<String> Name = user.getInterests();
        UserNode userNode2 = userNode1;

//this method will save the userNode3 into db and create userNode3 node
        recommendationCommandServiceImpl.saveUserToDb(userNode2);
        log.info("userNode3 is created");


//this method creates relationship follows between userNode3 and topics
        recommendationCommandServiceImpl.userFollowsTopic(userName, Name);
        log.info("follows relationship is created");

    }
}