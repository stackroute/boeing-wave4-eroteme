package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<Question> getAllUnansweredQuestionsForRegisteredUser(String userName) {
        return userRepository.findAllUnansweredQuestionsForRegisteredUser(userName);
    }

    @Override
    public List<QuestionRequested> getAllUnansweredQuestionsForGuestUser() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return Objects.requireNonNull(restTemplate.exchange(questionAndAnswerUrl + "questions", HttpMethod.GET, null, new ParameterizedTypeReference<List<QuestionRequested>>() {
            }).getBody()).stream().filter(questionRequested -> questionRequested.getAnswerDocuments().isEmpty()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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
    public List<User> getAllUsersRelatedToQuestion(long questionId) {
        return userRepository.findAllUsersRelatedToTopic(questionId);
    }

    @Override
    public List<Question> getTrendingQuestionsForRegisteredUser(String username) {
        return userRepository.getAllTrendingQuestionsForRegisteredUser(username);
    }

    @Override
    public List<Question> getAllAcceptedAnswersOfDomain(String username) {
        return userRepository.getAllAcceptedAnswersForDomain(username);
    }

    @Override
    public List<QuestionRequested> getTrendingQuestionsForGuestUser() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return restTemplate.exchange(questionAndAnswerUrl + "questions", HttpMethod.GET, null, new ParameterizedTypeReference<List<QuestionRequested>>() {
            }).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}