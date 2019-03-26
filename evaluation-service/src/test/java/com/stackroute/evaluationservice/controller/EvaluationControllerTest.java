//package com.stackroute.evaluationservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.evaluationservice.domain.Actions;
//import com.stackroute.evaluationservice.domain.Question;
//import com.stackroute.evaluationservice.domain.QuestionDTO;
//import com.stackroute.evaluationservice.service.EvaluationService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class EvaluationControllerTest {
//    private static final String TEST_QUESTION = "TEST_QUESTION";
//    private static final Question QUESTION = Question.builder().question(TEST_QUESTION).questionId(1).build();
//    private static final QuestionDTO QUESTION_DTO = QuestionDTO.builder().action(Actions.POST_QUESTION).question(TEST_QUESTION).questionId(1).build();
//    @MockBean
//    private RabbitTemplate rabbitTemplate;
//    @MockBean
//    private RestTemplate restTemplate;
//    @Autowired
//    private EvaluationController evaluationController;
//    @MockBean
//    private EvaluationService evaluationService;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(evaluationController).build();
//        ReflectionTestUtils.setField(evaluationController, "exchange", "sampleexchange");
//        ReflectionTestUtils.setField(evaluationController, "routingKey", "sampleroutingkey");
//        ReflectionTestUtils.setField(evaluationController, "questionAndAnswerUrl", "sampleurl");
//    }
//
//    @Test
//    public void testForGettingDataFromDb() throws Exception {
//        List<Question> webResults = new ArrayList<>();
//        when(evaluationService.searchInDb(TEST_QUESTION)).thenReturn(CompletableFuture.completedFuture(QUESTION));
//        when(evaluationService.searchInWeb()).thenReturn(CompletableFuture.completedFuture(webResults));
//        mockMvc.perform(MockMvcRequestBuilders.post("/result", QUESTION_DTO)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(webResults.add(QUESTION))))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private String asJsonString(Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//}