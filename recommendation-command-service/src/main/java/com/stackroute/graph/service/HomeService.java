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


    public User userfollowstopic(String userName, String Name) {

        return userRepository.userfollowstopicrelationship(userName, Name);

    }

    public void saveTopicToDb(Topic topic) {

        topicRepository.save(topic);
    }


    public User getByUser(int reputation) {

        return userRepository.getByUser(reputation);
    }

    public Question questionbelongstopic(int questionId, String Name) {

        return questionRepository.questionbelongstopicrelationship(questionId, Name);
    }

    public User useransweredanswer(String userName, int answerId) {

        return answerRepository.useransweredanswerrelationship(userName, answerId);
    }

    public User userviewedquestion(String userName, int questionId) {

        return questionRepository.userviewedquestionrelationship(userName, questionId);

    }

    public Answer answerisanswerofquestion(int answerId, int questionId) {

        return questionRepository.answerisanswerofquestionrelationship(answerId, questionId);

    }

    public User useraskedquestion(String userName, int questionId) {

        return questionRepository.useraskedquestionrelationship(userName, questionId);

    }

    public User useracceptedanswer(String userName, int answerId) {

        return answerRepository.useracceptedanswerrelationship(userName, answerId);

    }

    public User userupvotedanswer(String userName, int answerId) {

        return answerRepository.userupvotedanswerrelationship(userName, answerId);

    }

    public User userdownvotedanswer(String userName, int answerId) {

        return answerRepository.userdownvotedanswerrelationship(userName, answerId);

    }

    public User userupvotequestion(String userName, int questionId) {

        return questionRepository.userupvotequestionrelationship(userName, questionId);

    }


    public User userdownvotequestion(String userName, int questionId) {

        return questionRepository.userdownvotequestionrelationship(userName, questionId);

    }



}
