package com.stackroute.searchservice.ServiceController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.searchservice.controller.SearchController;
import com.stackroute.searchservice.domain.Question;
import com.stackroute.searchservice.domain.Topic;
import com.stackroute.searchservice.domain.User;
import com.stackroute.searchservice.repository.SearchRepository;
import com.stackroute.searchservice.service.RabbitService;
import com.stackroute.searchservice.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ServiceControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private com.stackroute.searchservice.domain.Question question;

    private Topic topic;
    @MockBean
    private SearchService searchService;
    @MockBean
    private SearchRepository searchRepository;
    @InjectMocks
    private SearchController searchController;
    @MockBean
    private RabbitService rabbitService;

    private List<Topic> topicList = new ArrayList<>();
    private List<Question> questionList=new ArrayList<>();
    private User user=new User();


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
        topic = new Topic();
        topic.setTopic("Angular");
        question = new Question();
        question.setQuestionId(1);
        question.setQuestion("what is angular");
        question.setDescription("xyz abc");
        question.setUpvotes(2);
        question.setTimestamp(56789);
        question.setDownvote(1);
        question.setAnswers(null);
        question.setComments(null);
        question.setUser(user);
        user.setEmailaddress("anjo@gmail.com");
        user.setFirstname("anjo");
        user.setImageurl("https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg");
        questionList.add(question);
        topic.setQuestions(questionList);
        topicList.add(topic);
    }

    @Test
    public void getAllTopics() throws Exception {
        when(searchService.getAllTopics()).thenReturn(topicList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/topics")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(topicList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTopicsFailure() throws Exception {
        when(searchService.getAllTopics()).thenReturn(topicList);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/topics")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(topicList)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTopic() throws Exception {
        when(searchService.getAllTopics()).thenReturn(topicList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/topic")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(topicList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTopicFailure() throws Exception {
        when(searchService.getAllTopics()).thenReturn(topicList);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/topic")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(topicList)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
