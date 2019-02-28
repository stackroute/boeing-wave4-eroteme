//package com.stackroute.controller;
//
//import com.stackroute.domain.Question;
//import com.stackroute.domain.User;
//import com.stackroute.exceptions.QuestionAlreadyExistsException;
//import com.stackroute.service.QuestionService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
////JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
//@RunWith(SpringRunner.class)
//
////Used when a test focuses only on Spring MVC components.
//@WebMvcTest
//public class QuestionControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    private Question question;
//
//    //Any existing single bean of the same type defined in the context will be replaced by the mock.
//    @MockBean
//    private QuestionService questionService;
//
//    @Mock
//    private RabbitTemplate rabbitTemplate;
//
//    //Inject the mocks as dependencies into QuestionServiceImpl
//    @InjectMocks
//    private QuestionController questionController;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
//        User user = new User("grace@gmail.com","Grace","https://previews.123rf.com/images/joloei/joloei1701/joloei170100028/70835595-letter-g-alphabet-with-damask-rose-abc-concept-type-as-logo-isolated-on-white-background.jpg");
//        List<String> topics = new ArrayList<>();
//        topics.add("Angular");
//        question = new Question(77, "What is the basics of angular?", null,topics, 0, 4578533, 0, user, null, null);
//    }
//
//    @Test
//    public void testSaveQuestionSuccess() throws QuestionAlreadyExistsException {
//        when(questionService.addQuestion(any())).thenReturn(question);
//        mockMvc.perform(post("/api/v1/question")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString("Successfully created")))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//}