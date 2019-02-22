package com.stackroute.graph.service;


import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.Question;
import com.stackroute.graph.model.Topic;
import com.stackroute.graph.model.User;
import com.stackroute.graph.repository.AnswerRepository;
import com.stackroute.graph.repository.QuestionRepository;
import com.stackroute.graph.repository.TopicRepository;
import com.stackroute.graph.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class HomeService {

    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private TopicRepository topicRepository;


    public HomeService(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.topicRepository = topicRepository;
    }


    public void saveUserToDb(User user) {
        userRepository.save(user);
    }


    public Collection<User> getUsers() {
        return userRepository.getAllUsers();
    }


    public void saveQuestionToDb(Question question) {
        questionRepository.save(question);
    }


    public Collection<Question> getQuestions() {

        return questionRepository.getAllQuestions();
    }


    public void saveAnswerToDb(Answer answer) {

        answerRepository.save(answer);
    }


    public Collection<Answer> getAnswers() {

        return answerRepository.getAllAnswers();
    }


    public void deleteAnswers(long answerId) {


        answerRepository.deleteById(answerId);
    }

//    public void deleteMovies(int released) {
//        movieRepository.deleteByReleased(released);
//    }

    public User userfollowstopic(int userId, String Name) {

        return userRepository.userfollowstopicrelationship(userId, Name);

    }

    public void saveTopicToDb(Topic topic) {

        topicRepository.save(topic);
    }


    public User getByUser(int reputation) {

        return userRepository.getByUser(reputation);
    }

    public Question questionbelongstopic(int questionId, int topicId) {

        return topicRepository.questionbelongstopicrelationship(questionId, topicId);
    }

    public User useransweredanswer(int userId, int answerId) {

        return answerRepository.useransweredanswerrelationship(userId, answerId);
    }

    public User userviewedquestion(int userId, int questionId) {

        return questionRepository.userviewedquestionrelationship(userId, questionId);

    }

    public Answer answerisanswerofquestion(int answerId, int questionId) {

        return questionRepository.answerisanswerofquestionrelationship(answerId, questionId);

    }

    public User useraskedquestion(int userId, int questionId) {

        return questionRepository.useraskedquestionrelationship(userId, questionId);

    }

    public User useracceptedanswer(int userId, int answerId) {

        return answerRepository.useracceptedanswerrelationship(userId, answerId);

    }

    public User userupvotedanswer(int userId, int answerId) {

        return answerRepository.userupvotedanswerrelationship(userId, answerId);

    }

    public User userdownvotedanswer(int userId, int answerId) {

        return answerRepository.userdownvotedanswerrelationship(userId, answerId);

    }

    public User userupvotequestion(int userId, int questionId) {

        return questionRepository.userupvotequestionrelationship(userId, questionId);

    }


    public User userdownvotequestion(int userId, int questionId) {

        return questionRepository.userdownvotequestionrelationship(userId, questionId);

    }


    public Collection<Topic> getTopics() {

        return topicRepository.getAllTopics();
    }
}
