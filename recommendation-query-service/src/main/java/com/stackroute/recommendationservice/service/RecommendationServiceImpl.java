package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Answer;
import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionNode;
import com.stackroute.recommendationservice.model.UserNode;
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

import java.util.*;
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
    public List<QuestionNode> getAllUnansweredQuestionsForRegisteredUser(String userName) {
        return userRepository.findAllUnansweredQuestionsForRegisteredUser(userName);
    }

    @Override
    public List<UserNode> getAllUsersRelatedToQuestion(long questionId) {
        return userRepository.findAllUsersRelatedToTopic(questionId);
    }

    @Override
    public List<QuestionNode> getTrendingQuestionsForRegisteredUser(String username) {
        return userRepository.getAllTrendingQuestionsForRegisteredUser(username);
    }

    @Override
    public List<QuestionNode> getAllAcceptedAnswersOfDomain(String username) {
        return userRepository.getAllAcceptedAnswersForDomain(username);
    }

    @Override
    public List<Question> getTrendingQuestionsForGuestUser() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return restTemplate.exchange(questionAndAnswerUrl + "questions", HttpMethod.GET, null, new ParameterizedTypeReference<List<Question>>() {
            }).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Question> getAllUnansweredQuestionsForGuestUser() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return Objects.requireNonNull(restTemplate.exchange(questionAndAnswerUrl + "questions", HttpMethod.GET, null, new ParameterizedTypeReference<List<Question>>() {
            }).getBody()).stream().filter(questionRequested -> questionRequested.getAnswer().isEmpty()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Question> getAllAcceptedAnswersForGuestUser() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            List<Question> questions = new ArrayList<>(Objects.requireNonNull(restTemplate.exchange(questionAndAnswerUrl + "questions", HttpMethod.GET, null, new ParameterizedTypeReference<List<Question>>() {
            }).getBody()));

            Map<Long, List<Answer>> questionListMap = questions.stream()
                    .collect(Collectors.toMap(Question::getQuestionId, question -> question.getAnswer().stream().filter(Answer::isAccepted).collect(Collectors.toList())));

            return questionListMap
                    .keySet()
                    .stream()
                    .flatMap(key -> questions.stream().filter(question -> question.getQuestionId() == key))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Question getDocumentByQuestionId(long questionId) {
        try {
            return restTemplate.getForObject(questionAndAnswerUrl.concat(Long.toString(questionId)), Question.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new Question();
        }
    }
}