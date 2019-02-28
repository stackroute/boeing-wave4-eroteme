package com.stackroute.recommendationcommandservice.service;


import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import com.stackroute.recommendationcommandservice.repository.AnswerRepository;
import com.stackroute.recommendationcommandservice.repository.QuestionRepository;
import com.stackroute.recommendationcommandservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    /*method to save user*/
    @Override
    public User saveUserToDb(User user) {
        userRepository.save(user);
        return user;
    }

    //method to get USERS//
    @Override
    public Collection<User> getUsers() {
        return userRepository.getAllUsers();
    }


    //method to add QUESTION//
    @Override
    public Question saveQuestionToDb(Question question) {
        questionRepository.save(question);
        return question;
    }


    //method to get QUESTIONS//
    @Override
    public Collection<Question> getQuestions() {

        return questionRepository.getAllQuestions();
    }


    //method to add ANSWER//
    @Override
    public Answer saveAnswerToDb(Answer answer) {

        answerRepository.save(answer);
        return answer;
    }

    //method to get ANSWERS//
    @Override
    public Collection<Answer> getAnswers() {

        return answerRepository.getAllAnswers();
    }


    //method to create relationship FOLLOWS between user and topic//
    @Override
    public User userFollowsTopic(String userName, String Name) {

        return userRepository.userFollowsTopicRelationship(userName, Name);

    }


    //method to get USERS by reputation//
    @Override
    public User getByUser(int reputation) {

        return userRepository.getByUser(reputation);
    }


    //method to create relationship QUESTION_OF between question and topic//
    @Override
    public Question questionBelongsTopic(int questionId, String Name) {

        return questionRepository.questionBelongsTopicRelationship(questionId, Name);
    }


    //method to create relationship ANSWERED between user and answer//
    @Override
    public User userAnsweredAnswer(String userName, int answerId) {

        return answerRepository.userAnsweredAnswerRelationship(userName, answerId);
    }


    //method to create relationship VIEWED between user and question//
    @Override
    public User userViewedQuestion(String userName, int questionId) {

        return questionRepository.userViewedQuestionRelationship(userName, questionId);

    }


    //method to create relationship ANSWER_OF between answer and question//
    @Override
    public Answer answerIsAnswerOfQuestion(int answerId, int questionId) {

        return questionRepository.answerIsAnswerOfQuestionRelationship(answerId, questionId);

    }


    //method to create relationship ASKED between user and question//
    @Override
    public User userAskedQuestion(String userName, int questionId) {

        return questionRepository.userAskedQuestionRelationship(userName, questionId);

    }

    //method to create relationship ACCEPTED between user and answer//
    @Override
    public User userAcceptedAnswer(String userName, int answerId) {

        return answerRepository.userAcceptedAnswerRelationship(userName, answerId);

    }


    //method to create relationship UPVOTED between user and answer//
    @Override
    public User userUpvotedAnswer(String userName, int answerId) {

        return answerRepository.userUpvotedAnswerRelationship(userName, answerId);

    }


    //method to create relationship DOWNVOTED between user and answer//
    @Override
    public User userDownvotedAnswer(String userName, int answerId) {

        return answerRepository.userDownvotedAnswerRelationship(userName, answerId);

    }


    //method to create relationship UPVOTE between user and question//
    @Override
    public User userUpvoteQuestion(String userName, int questionId) {

        return questionRepository.userUpvoteQuestionRelationship(userName, questionId);

    }


    //method to create relationship DOWNVOTE between user and question//
    @Override
    public User userDownvoteQuestion(String userName, int questionId) {

        return questionRepository.userDownvoteQuestionRelationship(userName, questionId);

    }


}
