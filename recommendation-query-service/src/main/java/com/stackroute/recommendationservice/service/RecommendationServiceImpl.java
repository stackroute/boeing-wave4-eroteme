package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.QuestionDocumentRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {


    private UserRepository userRepository;

    private QuestionDocumentRepository questionDocumentRepository;
    @Autowired
    public RecommendationServiceImpl(UserRepository userRepository, QuestionDocumentRepository questionDocumentRepository) {
        this.questionDocumentRepository = questionDocumentRepository;
        this.userRepository = userRepository;
        System.out.println("bbb");

    }

    @Override
    public List<Question> getAllUnansweredQuestions(String userName) {
        return userRepository.findAllUnansweredQuestion(userName);
    }

    @Override
    public QuestionRequested getDocumentByQuestionId(long questionId) {
        Optional<QuestionRequested> questionDocument = questionDocumentRepository.findById(questionId);
        return questionDocument.orElseGet(() -> {
            log.info("Document not found for the ID {}. Returning empty document", questionId);
            return questionDocument.get();
        });
    }

    @Override
    public List<User> getAllUsers() {
//        return userRepository.findAllUsers();
        return null;
    }

    @Override
    public List<Question> getTrendingQuestionsForUser(String username) {
        return userRepository.getAllTrendingQuestionsForUser(username);
    }

    // This method is added only for testing purpose
    @Override
    public QuestionRequested insertIntoDb(QuestionRequested questionRequested) {
        return questionDocumentRepository.save(questionRequested);
    }



}