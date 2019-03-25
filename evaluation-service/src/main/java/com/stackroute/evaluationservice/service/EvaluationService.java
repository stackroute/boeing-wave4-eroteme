package com.stackroute.evaluationservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.evaluationservice.domain.Question;
import com.stackroute.evaluationservice.domain.QuestionDTO;
import com.stackroute.evaluationservice.domain.UserNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
@Slf4j
public class EvaluationService {
    private RestTemplate restTemplate;
    @Value("${questionAndAnswerUrl}")
    private String questionAndAnswerUrl;
    @Value("${recommendNotifyUrl}")
    private String recommendNotifyUrl;
    @Value("${webcrawlerurl}")
    private String webcrawlerurl;

    @Autowired
    public EvaluationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<Question> searchInDb(String question) {
        try {
            Question questionFromDb = restTemplate.getForObject(questionAndAnswerUrl.concat("getquestion?question=").concat(question), Question.class);
            log.info("Question from Q&A db is {}", questionFromDb);
            return completedFuture(questionFromDb);
        } catch (Exception e) {
            e.printStackTrace();
            return completedFuture(new Question());
        }
    }

    //TODO After AAS is ready
    @Async
    @HystrixCommand(fallbackMethod = "searchInWebDefault")
    public CompletableFuture<List<Question>> searchInWeb() {
        List<Question> questions = restTemplate.exchange(webcrawlerurl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Question>>() {
        }).getBody();
        log.info("Results from web are : {}", questions);

        return completedFuture(questions);
    }

    public List<Question> searchInWebDefault() {
        log.info("Web crawler has crashed!");
        return new ArrayList<>();
    }

    @Async
    @HystrixCommand(fallbackMethod = "notifyDefault")
    public CompletableFuture<List<UserNode>> notifyUsersForTheQuestion(QuestionDTO questionDTO) {
        try {
            log.info("Getting eligible users for notification");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return completedFuture(restTemplate.exchange(recommendNotifyUrl.concat("?question=").concat(questionDTO.getQuestion()).concat("&action=").concat(questionDTO.getAction().name()), HttpMethod.GET, null, new ParameterizedTypeReference<List<UserNode>>() {
            }).getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return completedFuture(Collections.emptyList());
        }
    }

    public List<UserNode> notifyDefault(QuestionDTO questionDTO) {
        log.info("Notification service has crashed!");
        return Collections.emptyList();
    }
}
