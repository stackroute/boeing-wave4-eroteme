package com.stackroute.evaluationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.evaluationservice.domain.*;
import com.stackroute.evaluationservice.service.EvaluationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EvaluationControllerTest {
    private static final String TEST_QUESTION = "TEST_QUESTION";
    private static final Question QUESTION = Question.builder().question(TEST_QUESTION).questionId(1).build();
    private static final String USERNAME = "USERNAME";
    private static final QuestionDTO QUESTION_DTO = QuestionDTO.builder().action(Actions.POST_QUESTION).user(User.builder().email(USERNAME).build()).question(TEST_QUESTION).questionId(1).build();
    private static final String SAMPLEEXCHANGE = "sampleexchange";
    private static final String SAMPLEROUTINGKEY = "sampleroutingkey";
    @MockBean
    private RabbitTemplate rabbitTemplate;
    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private EvaluationController evaluationController;
    @MockBean
    private EvaluationService evaluationService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(evaluationController).build();
        ReflectionTestUtils.setField(evaluationController, "exchange", SAMPLEEXCHANGE);
        ReflectionTestUtils.setField(evaluationController, "routingKey", SAMPLEROUTINGKEY);
        ReflectionTestUtils.setField(evaluationController, "questionAndAnswerUrl", "sampleurl");
    }

    @Test
    public void testForGettingDataFromDb() throws Exception {
        List<Question> webResults = new ArrayList<>();
        when(evaluationService.searchInDb(TEST_QUESTION)).thenReturn(CompletableFuture.completedFuture(QUESTION));
        when(evaluationService.searchInWeb()).thenReturn(CompletableFuture.completedFuture(webResults));
        mockMvc.perform(MockMvcRequestBuilders.post("/result")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(QUESTION_DTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForNotifyingUsers() throws Exception {
        List<Question> webResults = new ArrayList<>();
        List<UserNode> userNodes = new ArrayList<>();
        userNodes.add(UserNode.builder().email("EMAIL").reputation(12).build());
        when(evaluationService.searchInDb(TEST_QUESTION)).thenReturn(CompletableFuture.completedFuture(null));
        when(evaluationService.searchInWeb()).thenReturn(CompletableFuture.completedFuture(webResults));
        when(evaluationService.notifyUsersForTheQuestion(QUESTION_DTO)).thenReturn(userNodes);
        mockMvc.perform(MockMvcRequestBuilders.post("/result")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(QUESTION_DTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}