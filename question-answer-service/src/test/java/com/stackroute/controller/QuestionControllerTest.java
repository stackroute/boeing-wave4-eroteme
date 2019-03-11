package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.*;
import com.stackroute.service.QuestionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
@RunWith(SpringRunner.class)

//Used when a test focuses only on Spring MVC components.
@WebMvcTest
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Question question;
    private Answer answer;
    private Comment comment;
    private Replies reply;

    //Any existing single bean of the same type defined in the context will be replaced by the mock.
    @MockBean
    private QuestionService questionService;

    @Mock
    private AmqpTemplate amqpTemplate;

    //Inject the mocks as dependencies into QuestionServiceImpl
    @InjectMocks
    private QuestionController questionController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
        User user = new User("grace@gmail.com", "Grace", "https://previews.123rf.com/images/joloei/joloei1701/joloei170100028/70835595-letter-g-alphabet-with-damask-rose-abc-concept-type-as-logo-isolated-on-white-background.jpg");
        List<String> topics = new ArrayList<>();
        topics.add("Angular");
        question = new Question(77, "What is the basics of angular?", null, topics, 0, 4578533, 0, user, null, null);
        answer = new Answer("Angular", false, 0, 0, 0, 1234, user, null);
        comment = new Comment("nice", 1234, 0, user, null);
        reply = new Replies("good", 0, 1234, user);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveQuestionSuccess() throws Exception {

        when(questionService.addQuestion(any())).thenReturn(question);
        mockMvc.perform(post("/api/v1/question")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addAnswerSuccess() throws Exception{
        when(questionService.addAnswer(77,answer)).thenReturn(question);

        when(questionService.addAnswer(anyInt(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/answer/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addCommentSuccess() throws Exception{
        when(questionService.addQuestionComment(77,comment)).thenReturn(question);

        when(questionService.addQuestionComment(anyInt(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/comment/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addCommentReplySuccess() throws Exception{
        List<Replies> repliesList = new ArrayList<>();
        repliesList.add(reply);
        when(questionService.addQuestionCommentReply(77,"abcd",repliesList)).thenReturn(question);

        when(questionService.addQuestionCommentReply(anyInt(),anyString(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/comment/reply/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addAnswerCommentSuccess() throws Exception{
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        when(questionService.addAnswerComment(77,"abcd",commentList)).thenReturn(question);

        when(questionService.addAnswerComment(anyInt(),anyString(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/answer/comment/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addAnswerCommentReplySuccess() throws Exception{
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        when(questionService.addAnswerCommentReply(77,"abcd",commentList)).thenReturn(question);

        when(questionService.addAnswerCommentReply(anyInt(),anyString(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/answer/comment/reply/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addQuestionUpvote() throws Exception{
        when(questionService.addQuestionUpvote(77)).thenReturn(question);

        when(questionService.addQuestionUpvote(anyInt())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/upvote/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addQuestionDownvote() throws Exception{
        when(questionService.addQuestionDownvote(77)).thenReturn(question);

        when(questionService.addQuestionDownvote(anyInt())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/downvote/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addAnswerUpvote() throws Exception{
        when(questionService.addAnswerUpvote(77,"abcd")).thenReturn(question);

        when(questionService.addAnswerUpvote(anyInt(),anyString())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/answer/upvote/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addAnswerDownvote() throws Exception{
        when(questionService.addAnswerDownvote(77,"abcd")).thenReturn(question);

        when(questionService.addAnswerDownvote(anyInt(),anyString())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/answer/downvote/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addAnswerAccept() throws Exception{
        when(questionService.addQuestionAnswerAccepted(77,"abcd")).thenReturn(question);

        when(questionService.addQuestionAnswerAccepted(anyInt(),anyString())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/answer/accept/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addQuestionCommentLikes() throws Exception{
        when(questionService.addQuestionCommentLikes(77,"abcd")).thenReturn(question);

        when(questionService.addQuestionCommentLikes(anyInt(),anyString())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/comment/reply/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addQuestionAnswerCommentLikes() throws Exception{
        when(questionService.addAnswerCommentLikes(77,answer)).thenReturn(question);

        when(questionService.addAnswerCommentLikes(anyInt(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/answer/comment/likes/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addQuestionCommentReplyLikes() throws Exception{
        when(questionService.addQuestionCommentReplyLikes(77,comment)).thenReturn(question);

        when(questionService.addQuestionCommentReplyLikes(anyInt(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/comment/reply/likes/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addAnswerCommentReplyLikes() throws Exception{
        when(questionService.addAnswerCommentReplyLikes(77,answer)).thenReturn(question);

        when(questionService.addAnswerCommentReplyLikes(anyInt(),any())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/question/answer/comment/reply/likes/77")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andExpect(MockMvcResultMatchers.status().isOk())
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
