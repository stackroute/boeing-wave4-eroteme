package com.stackroute.graph.service;


import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.Question;
import com.stackroute.graph.model.User;
import com.stackroute.graph.repository.AnswerRepository;
import com.stackroute.graph.repository.QuestionRepository;
import com.stackroute.graph.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class HomeService {

    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public HomeService(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
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

//    public Collection<Answer> getAnswered() {
//        return answerRepository.getAllAnswered();
//    }

    public void deleteAnswers(int answerId) {
        answerRepository.deleteById(answerId);
    }
}
