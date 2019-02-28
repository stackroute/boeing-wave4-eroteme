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

    @Override
    public User saveUserToDb(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public Question saveQuestionToDb(Question question) {
        questionRepository.save(question);
        return question;
    }

    @Override
    public Collection<Question> getQuestions() {

        return questionRepository.getAllQuestions();
    }

    @Override
    public Answer saveAnswerToDb(Answer answer) {

        answerRepository.save(answer);
        return answer;
    }

    @Override
    public Collection<Answer> getAnswers() {

        return answerRepository.getAllAnswers();
    }

    @Override
    public User userFollowsTopic(String userName, String Name) {

        return userRepository.userFollowsTopicRelationship(userName, Name);

    }

    @Override
    public User getByUser(int reputation) {

        return userRepository.getByUser(reputation);
    }

    @Override
    public Question questionBelongsTopic(int questionId, String Name) {

        return questionRepository.questionBelongsTopicRelationship(questionId, Name);
    }

    @Override
    public User userAnsweredAnswer(String userName, int answerId) {

        return answerRepository.userAnsweredAnswerRelationship(userName, answerId);
    }

    @Override
    public User userViewedQuestion(String userName, int questionId) {

        return questionRepository.userViewedQuestionRelationship(userName, questionId);

    }

    @Override
    public Answer answerIsAnswerOfQuestion(int answerId, int questionId) {

        return questionRepository.answerIsAnswerOfQuestionRelationship(answerId, questionId);

    }

    @Override
    public User userAskedQuestion(String userName, int questionId) {

        return questionRepository.userAskedQuestionRelationship(userName, questionId);

    }

    @Override
    public User userAcceptedAnswer(String userName, int answerId) {

        return answerRepository.userAcceptedAnswerRelationship(userName, answerId);

    }

    @Override
    public User userUpvotedAnswer(String userName, int answerId) {

        return answerRepository.userUpvotedAnswerRelationship(userName, answerId);

    }

    @Override
    public User userDownvotedAnswer(String userName, int answerId) {

        return answerRepository.userDownvotedAnswerRelationship(userName, answerId);

    }

    @Override
    public User userUpvoteQuestion(String userName, int questionId) {

        return questionRepository.userUpvoteQuestionRelationship(userName, questionId);

    }

    @Override

    public User userDownvoteQuestion(String userName, int questionId) {

        return questionRepository.userDownvoteQuestionRelationship(userName, questionId);

    }


}
