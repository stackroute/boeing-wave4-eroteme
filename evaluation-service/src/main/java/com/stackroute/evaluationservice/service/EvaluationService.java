package com.stackroute.evaluationservice.service;

import com.stackroute.evaluationservice.domain.Notification;
import com.stackroute.evaluationservice.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
public class EvaluationService {
    private RestTemplate restTemplate;
    @Value("${questionAndAnswerUrl}")
    private String questionAndAnswerUrl;
    @Value("${recommendNotifyUrl}")
    private String recommendNotifyUrl;

    @Autowired
    public EvaluationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<Question> searchInDb(String question) {
        try {
            //TODO add a method in controller to find document by question string after branch is merged
            return completedFuture(restTemplate.getForObject(questionAndAnswerUrl.concat(question), Question.class));
        } catch (Exception e) {
            e.printStackTrace();
            return completedFuture(new Question());
        }
    }

    //TODO After AAS is ready
    public Question searchInWeb(String question) {
        return new Question();
    }

    @Async
    public CompletableFuture<List<Notification>> notifyUsersForTheQuestion(String question) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return completedFuture(restTemplate.exchange(recommendNotifyUrl.concat(question), HttpMethod.GET, null, new ParameterizedTypeReference<List<Notification>>() {
            }).getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return completedFuture(Collections.emptyList());
        }
    }
}
