package com.stackroute.recommendationcommandservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import com.stackroute.recommendationcommandservice.repository.AnswerRepository;
import com.stackroute.recommendationcommandservice.repository.QuestionRepository;
import com.stackroute.recommendationcommandservice.repository.UserRepository;
import com.stackroute.recommendationcommandservice.service.RecommendationCommandServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class RecommendationControllerTest {

    private static final User USER = User.builder().userName("kate").reputation(1).build();
    private static final Question QUESTION = Question.builder().questionId(12).questionString("what is java").timestamp(34544).upVote(32).downVote(1).build();
    private static final Answer ANSWER = Answer.builder().answerId(21).answerString("Dont do it").build();

    @MockBean
    private RecommendationCommandServiceImpl recommendationCommandService;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AnswerRepository answerRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private RecommendationController recommendationController;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addUser() throws Exception {
        when(userRepository.save(USER)).thenReturn(USER);
        mockMvc.perform(MockMvcRequestBuilders.post("/adduser")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addQuestion() throws Exception {
        when(questionRepository.save(QUESTION)).thenReturn(QUESTION);
        mockMvc.perform(MockMvcRequestBuilders.post("/addquestion")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void addAnswer() throws Exception {
        when(answerRepository.save(ANSWER)).thenReturn(ANSWER);
        mockMvc.perform(MockMvcRequestBuilders.post("/addanswer")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(ANSWER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getAllUsers() throws Exception {
        when(userRepository.getAllUsers()).thenReturn(Collections.singletonList(USER));
        mockMvc.perform(MockMvcRequestBuilders.get("/getusers")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllQuestions() throws Exception {
        when(questionRepository.getAllQuestions()).thenReturn(Collections.singletonList(QUESTION));
        mockMvc.perform(MockMvcRequestBuilders.get("/getquestions")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getAllAnswers() throws Exception {
        when(answerRepository.getAllAnswers()).thenReturn(Collections.singletonList(ANSWER));
        mockMvc.perform(MockMvcRequestBuilders.get("/getanswers")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(ANSWER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getUserByReputation() throws Exception {
        when(userRepository.getByUser(1)).thenReturn(USER);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{reputation}", 1)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void createRelationshipfollows() throws Exception {
        when(userRepository.userfollowstopicrelationship("meghana", "testing")).thenReturn(USER);
        mockMvc.perform(MockMvcRequestBuilders.get("/follows/{userName}/{name}", "meghana", "testing")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void createRelationshipbelongs() throws Exception {
        when(questionRepository.questionbelongstopicrelationship(12, "pipes")).thenReturn(QUESTION);
        mockMvc.perform(MockMvcRequestBuilders.get("/belongs/{questionId}/{name}", "12", "pipes")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void createRelationshipanswered() throws Exception {
        when(answerRepository.useransweredanswerrelationship("srinidhi", 201)).thenReturn(USER);
        mockMvc.perform(MockMvcRequestBuilders.get("/answered/{userName}/{answerId}", "srinidhi", 201)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void createRelationshipviewed() throws Exception {
        when(questionRepository.userviewedquestionrelationship("anirudh", 101)).thenReturn(USER);
        mockMvc.perform(MockMvcRequestBuilders.get("/viewed/{userName}/{questionId}", "anirudh", 101)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createRelationshipanswerof() throws Exception {
        when(questionRepository.answerisanswerofquestionrelationship(202, 101)).thenReturn(ANSWER);
        mockMvc.perform(MockMvcRequestBuilders.get("/answerof/{answerId}/{questionId}", 201, 101)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(ANSWER)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void createRelationshipasked() {
    }

    @Test
    public void createRelationshipaccepted() {
    }

    @Test
    public void createRelationshipupvoted() {
    }

    @Test
    public void createRelationshipdownvoted() {
    }

    @Test
    public void createRelationshipUpvoted() {
    }

    @Test
    public void createRelationshipDownvoted() {
    }
}