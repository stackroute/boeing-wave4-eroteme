package com.stackroute.evaluationservice.service;

import com.stackroute.evaluationservice.domain.Question;
import com.stackroute.evaluationservice.domain.QuestionDTO;
import com.stackroute.evaluationservice.domain.UserNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    @Async
    public CompletableFuture<List<Question>> searchInWeb() {
        List<Question> questions;
        try {
            questions = restTemplate.exchange(webcrawlerurl, HttpMethod.GET, null, new ParameterizedTypeReference<List<List<Question>>>() {
            }).getBody().stream().flatMap(Collection::stream).collect(Collectors.toList());
            log.info("Results from web are : {}", questions);

        } catch (Exception e) {
            e.printStackTrace();
            questions = new ArrayList<>();
        }

        return completedFuture(questions);
    }

    public List<UserNode> notifyUsersForTheQuestion(QuestionDTO questionDTO) {
        try {
            log.info("Getting eligible users for notification");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            try {

                restTemplate.postForEntity(questionAndAnswerUrl.concat("question"), new ResponseEntity<>(questionDTO, HttpStatus.OK), Question.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(5000);
            List<UserNode> userNodes = restTemplate.exchange(recommendNotifyUrl.concat("?question=").concat(questionDTO.getQuestion()).concat("&action=").concat(questionDTO.getAction().name()), HttpMethod.GET, null, new ParameterizedTypeReference<List<UserNode>>() {
            }).getBody();
            return userNodes;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
