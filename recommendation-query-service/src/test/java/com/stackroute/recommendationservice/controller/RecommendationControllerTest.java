package com.stackroute.recommendationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.model.AnswerRequested;
import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.QuestionDocumentRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import com.stackroute.recommendationservice.service.RecommendationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RecommendationControllerTest {
    private static final int QUESTION_ID = 3;
    private static final Question QUESTION_ONE = Question.builder().questionId(QUESTION_ID).upvote(20).build();
    private static final Question QUESTION_TWO = Question.builder().questionId(QUESTION_ID).upvote(0).build();
    private static final AnswerRequested ANSWER_REQUESTED = AnswerRequested.builder().build();
    private static final QuestionRequested QUESTION_DOCUMENT_ONE = QuestionRequested.builder()
            .answerDocuments(Arrays.asList(ANSWER_REQUESTED, ANSWER_REQUESTED, ANSWER_REQUESTED, ANSWER_REQUESTED, ANSWER_REQUESTED))
            .build();
    private static final Question QUESTION_TEST = Question.builder().questionId(QUESTION_ID).build();
    private static final User USER = User.builder().username("Sunidhi").reputation(100).build();
    private static final QuestionRequested QUESTION_DOCUMENT_TWO = QuestionRequested.builder()
            .answerDocuments(Collections.singletonList(ANSWER_REQUESTED)).build();
    private static final String USERNAME = "USERNAME";
    private static final String TOPIC = "TOPIC";
    @MockBean
    private RecommendationServiceImpl recommendationService;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private QuestionDocumentRepository questionDocumentRepository;
    @Autowired
    private RecommendationController recommendationController;
    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recommendationController).build();
        ReflectionTestUtils.setField(recommendationController, "questionUpvoteThreshold", 10);
        ReflectionTestUtils.setField(recommendationController, "numberOfAnswersThreshold", 5);
    }

    @Test
    public void testForTrendingQuestionsPresent() throws Exception {
        when(recommendationService.getTrendingQuestionsForUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_ONE));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/trending?username=USERNAME&topic=TOPIC")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForTrendingQuestionsNotPresent() throws Exception {
        when(recommendationService.getTrendingQuestionsForUser(USERNAME)).thenReturn(Collections.emptyList());
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/trending?username=USERNAME&topic=TOPIC")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testForUpvotesLessThanThreshold() throws Exception {
        when(recommendationService.getTrendingQuestionsForUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_TWO));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/trending?username=USERNAME&topic=TOPIC")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testForAnswersLessThanThreshold() throws Exception {
        when(recommendationService.getTrendingQuestionsForUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_ONE));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_TWO);
        mockMvc.perform(MockMvcRequestBuilders.get("/trending?username=USERNAME&topic=TOPIC")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testForTopicRelatedUsersArePresent() throws Exception {
        when(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).thenReturn(Collections.singletonList(USER));
        mockMvc.perform(MockMvcRequestBuilders.get("/notifyUsers", QUESTION_ONE)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
//    MockMvcRequestBuilders

    @Test
    public void testForTopicRelatedUsersAreNotPresent() throws Exception {
        when(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.post("/notifyUsers", QUESTION_ONE)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}