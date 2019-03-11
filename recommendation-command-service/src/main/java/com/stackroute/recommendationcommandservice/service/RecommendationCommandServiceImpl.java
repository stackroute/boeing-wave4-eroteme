package com.stackroute.recommendationcommandservice.service;


import com.stackroute.recommendationcommandservice.model.AnswerNode;
import com.stackroute.recommendationcommandservice.model.QuestionNode;
import com.stackroute.recommendationcommandservice.model.UserNode;
import com.stackroute.recommendationcommandservice.repository.AnswerRepository;
import com.stackroute.recommendationcommandservice.repository.QuestionRepository;
import com.stackroute.recommendationcommandservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class RecommendationCommandServiceImpl implements RecommendationCommandService {
    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public RecommendationCommandServiceImpl(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    /*method to save userDTO*/
    @Override
    public UserNode saveUserToDb(UserNode userNode) {
        userRepository.save(userNode);
        log.info("userNode node is created");
        return userNode;
    }


    //method to get USERS//
    @Override
    public Collection<UserNode> getUsers() {
        return userRepository.getAllUsers();
    }


    //method to add QUESTION//
    @Override
    public QuestionNode saveQuestionToDb(QuestionNode questionNode) {
        questionRepository.save(questionNode);
        log.info("questionNode is posted and saved");
        return questionNode;
    }


    //method to get QUESTIONS//
    @Override
    public Collection<QuestionNode> getQuestions() {

        return questionRepository.getAllQuestions();
    }


    //method to add ANSWER//
    @Override
    public AnswerNode saveAnswerToDb(AnswerNode answerNode) {


        answerRepository.save(answerNode);
        log.info("answerNode is posted and saved");
        log.info("answerNode is set to accepted");
        return answerNode;
    }

    //method to get ANSWERS//
    @Override
    public Collection<AnswerNode> getAnswers() {

        return answerRepository.getAllAnswers();
    }


    //method to create relationship FOLLOWS between userDTO and topic//
    @Override
    public UserNode userFollowsTopic(String userName, List<String> topicList) {

        topicList.forEach(topic -> {
            log.info("username is {} and topics are {}", userName, topic);
            UserNode user = userRepository.userFollowsTopicRelationship(userName, topic);
            log.info("follows relationship  is created");
        });
        return userRepository.findById(userName).orElse(new UserNode());
    }


    //method to get USERS by reputation//
    @Override
    public UserNode getByUser(int reputation) {

        return userRepository.getByUser(reputation);
    }


    //method to create relationship QUESTION_OF between question and topic//
    @Override
    public QuestionNode questionBelongsTopic(int questionId, List<String> Name) {
        log.info("question belongs to this topic relationship is created");

        return questionRepository.questionBelongsTopicRelationship(questionId, Name);
    }


    //method to create relationship ANSWERED between userDTO and answerDTO//
    @Override
    public UserNode userAnsweredAnswer(String userName, String answerString) {

        return answerRepository.userAnsweredAnswerRelationship(userName, answerString);
    }


    //method to create relationship VIEWED between userDTO and question//
    @Override
    public UserNode userViewedQuestion(String userName, int questionId) {

        return questionRepository.userViewedQuestionRelationship(userName, questionId);

    }


    //method to create relationship ANSWER_OF between answerDTO and question//
    @Override
    public AnswerNode answerIsAnswerOfQuestion(String answerString, int questionId) {
        log.info("relationship answerNode of is created");

        return questionRepository.answerIsAnswerOfQuestionRelationship(answerString, questionId);

    }


    //method to create relationship ASKED between userDTO and question//
    @Override
    public UserNode userAskedQuestion(String userName, int questionId) {

        return questionRepository.userAskedQuestionRelationship(userName, questionId);

    }

    //method to create relationship ACCEPTED between userDTO and answerDTO//
    @Override
    public UserNode userAcceptedAnswer(String userName, String answerString) {

        return answerRepository.userAcceptedAnswerRelationship(userName, answerString);

    }


    //method to create relationship UPVOTED between userDTO and answerDTO//
    @Override
    public UserNode userUpvotedAnswer(String userName, String answerString) {

        return answerRepository.userUpvotedAnswerRelationship(userName, answerString);

    }


    //method to create relationship DOWNVOTED between userDTO and answerDTO//
    @Override
    public UserNode userDownvotedAnswer(String userName, String answerString) {

        return answerRepository.userDownvotedAnswerRelationship(userName, answerString);

    }


    //method to create relationship UPVOTE between userDTO and question//
    @Override
    public UserNode userUpvoteQuestion(String userName, int questionId) {

        return questionRepository.userUpvoteQuestionRelationship(userName, questionId);

    }


    //method to create relationship DOWNVOTE between userDTO and question//
    @Override
    public UserNode userDownvoteQuestion(String userName, int questionId) {

        return questionRepository.userDownvoteQuestionRelationship(userName, questionId);

    }


}
