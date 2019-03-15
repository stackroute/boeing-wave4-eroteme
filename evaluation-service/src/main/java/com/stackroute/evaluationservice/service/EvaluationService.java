package com.stackroute.evaluationservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.evaluationservice.domain.Notification;
import com.stackroute.evaluationservice.domain.Question;
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
    @Async
    @HystrixCommand(fallbackMethod = "searchInWebDefault")
    public CompletableFuture<List<Question>> searchInWeb(String question) {
        return null;
    }

    public CompletableFuture<List<Question>> searchInWebDefault() {
        log.info("Web crawler has crashed!");
        return completedFuture(Collections.emptyList());
    }

    @Async
    @HystrixCommand(fallbackMethod = "notifyDefault")
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

    public CompletableFuture<List<Notification>> notifyDefault() {
        log.info("Notification service has crashed!");
        return completedFuture(Collections.emptyList());
    }
}
