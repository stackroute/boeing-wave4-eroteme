package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.AnswerRequested;
import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

    private UserRepository userRepository;
    private RestTemplate restTemplate;
    @Value("${questionAndAnswerUrl}")
    private String questionAndAnswerUrl;

    @Autowired
    public RecommendationServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Question> getAllUnansweredQuestions(String userName) {
        return userRepository.findAllUnansweredQuestion(userName);
    }

    @Override
    public QuestionRequested getDocumentByQuestionId(long questionId) {
        try {
            return restTemplate.getForObject(questionAndAnswerUrl.concat(Long.toString(questionId)), QuestionRequested.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new QuestionRequested();
        }
    }

    @Override
    public List<User> getAllUsersRelatedToQuestion(long questionID) {
        return userRepository.findAllUsersOfTopic(questionID);
    }

    @Override
    public List<Question> getTrendingQuestionsForUser(String username) {
        return userRepository.getAllTrendingQuestionsForUser(username);
    }

    @Override
    public List<AnswerRequested> getAllAcceptedAnswerOfDomain(String userName) {
        return null;
    }
}