package com.stackroute.evaluationservice.service;

import com.stackroute.evaluationservice.domain.Question;
import com.stackroute.evaluationservice.domain.QuestionDTO;
import com.stackroute.evaluationservice.domain.User;
import com.stackroute.evaluationservice.domain.UserNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EvaluationServiceTest {
    public static final String EMAIL = "EMAIL";
    public static final UserNode USER_NODE = UserNode.builder().username(EMAIL).reputation(1).build();
    private static final String TEST_QUESTION = "TEST_QUESTION";
    public static final QuestionDTO QUESTION_DTO = QuestionDTO.builder().question(TEST_QUESTION).user(User.builder().email(EMAIL).build()).build();
    private static final Question QUESTION = Question.builder().question(TEST_QUESTION).questionId(1).build();
    private static final String Q_A_URL = "Q&A_URL";
    private static final String RECOMMEND_URL = "RECOMMEND_URL";
    private static final String WEB_URL = "WEB_URL";
    @InjectMocks
    private EvaluationService evaluationService;
    @Mock
    private RestTemplate restTemplate;


    @Before
    public void setUp() {
        ReflectionTestUtils.setField(evaluationService, "questionAndAnswerUrl", Q_A_URL);
        ReflectionTestUtils.setField(evaluationService, "recommendNotifyUrl", RECOMMEND_URL);
        ReflectionTestUtils.setField(evaluationService, "webcrawlerurl", WEB_URL);
    }

    @Test
    public void testForSearchInDb() {
        when(restTemplate.getForObject(Q_A_URL.concat("getquestion?question=").concat(TEST_QUESTION), Question.class))
                .thenReturn(QUESTION);
        assertThat(evaluationService.searchInDb(TEST_QUESTION)).isCompleted();
    }

    @Test
    public void testForSearchInWeb() {
        List<List<Question>> questions = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        questionList.add(QUESTION);
        questions.add(questionList);
        when(restTemplate.exchange(WEB_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<List<Question>>>() {
        }).getBody()).thenReturn(questions);
        assertThat(evaluationService.searchInWeb()).isCompleted();
    }

    @Test
    public void testForNotifyingUsers() {
        List<UserNode> userNodes = new ArrayList<>();
        userNodes.add(USER_NODE);

        when(restTemplate.postForEntity(Q_A_URL.concat("question"), new HttpEntity<>(QUESTION_DTO), Question.class)).thenReturn(new ResponseEntity<>(QUESTION, HttpStatus.OK));
        when(restTemplate.exchange(RECOMMEND_URL, HttpMethod.POST, new HttpEntity<>(QUESTION_DTO), new ParameterizedTypeReference<List<UserNode>>() {
        })).thenReturn(new ResponseEntity<>(userNodes, HttpStatus.OK));
        assertThat(evaluationService.notifyUsersForTheQuestion(QUESTION_DTO)).containsExactly(USER_NODE);
    }
}