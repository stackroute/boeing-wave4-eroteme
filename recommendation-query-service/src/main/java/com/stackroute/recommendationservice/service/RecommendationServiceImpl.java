package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.domain.Answer;
import com.stackroute.recommendationservice.domain.Question;
import com.stackroute.recommendationservice.domain.QuestionNode;
import com.stackroute.recommendationservice.domain.UserNode;
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

import java.util.ArrayList;
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

    /**
     * @param userName email address of the user
     * @return returns all the unanswered questions related to topics  which user folllows
     */

    @Override
    public List<QuestionNode> getAllUnansweredQuestionsForRegisteredUser(String userName) {
        return userRepository.findAllUnansweredQuestionsForRegisteredUser(userName);
    }

    /**
     * @param question Question  of the posted question
     * @return List of eligible users who follow topics related to posted question
     */
    @Override
    public List<UserNode> getAllUsersRelatedToQuestion(String question) {

        return userRepository.findAllUsersRelatedToTopic(question);
    }

    /**
     * @param username username of the registered user
     * @return trending questions for the registered user
     */
    @Override
    public List<QuestionNode> getTrendingQuestionsForRegisteredUser(String username) {
        return userRepository.getAllTrendingQuestionsForRegisteredUser(username);
    }

    /**
     *
     * @param username username of the registered user
     * @return accepted questions related to the topic user follows
     */
    @Override
    public List<QuestionNode> getAllAcceptedAnswersOfDomain(String username) {
        return userRepository.getAllAcceptedAnswersForDomain(username);
    }

    /**
     *
     * @return trending questions for the guest user
     */
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

    /**
     *
     * @return unanswered questions for the guest user
     */
    @Override
    public List<Question> getAllUnansweredQuestionsForGuestUser() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return Objects.requireNonNull(restTemplate.exchange(questionAndAnswerUrl + "questions", HttpMethod.GET, null, new ParameterizedTypeReference<List<Question>>() {
            }).getBody()).stream().filter(questionRequested -> questionRequested.getAnswer() == null || questionRequested.getAnswer().isEmpty()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     *
     * @return accepted questions for the guest user
     */
    @Override
    public List<Question> getAllAcceptedAnswersForGuestUser() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            List<Question> questions = new ArrayList<>(Objects.requireNonNull(restTemplate.exchange(questionAndAnswerUrl + "questions", HttpMethod.GET, null, new ParameterizedTypeReference<List<Question>>() {
            }).getBody()));
            questions.forEach(question -> question.setAnswer(question.getAnswer().stream().filter(Answer::isAccepted).collect(Collectors.toList())));
            return questions.stream().
                    filter(question -> !question.getAnswer().isEmpty()).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     *
     * @param questionId question id of the the required question
     * @return question document
     */
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