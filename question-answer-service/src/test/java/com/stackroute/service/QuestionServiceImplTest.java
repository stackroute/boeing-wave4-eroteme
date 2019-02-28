package com.stackroute.service;

import com.stackroute.domain.Question;
import com.stackroute.domain.User;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.repository.QuestionRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QuestionServiceImplTest {
    private Question question;

    //Create a mock for QusetionRepository
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    //Inject the mocks as dependencies into QuestionServiceImpl
    @InjectMocks
    private QuestionServiceImpl questionService;
    List<Question> list = null;

    @Before
    public void setUp() throws Exception {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        User user = new User("grace@gmail.com","Grace","https://previews.123rf.com/images/joloei/joloei1701/joloei170100028/70835595-letter-g-alphabet-with-damask-rose-abc-concept-type-as-logo-isolated-on-white-background.jpg");
        List<String> topics = new ArrayList<>();
        topics.add("Angular");
        question = new Question(77, "What is the basics of angular?", null,topics, 0, 4578533, 0, user, null, null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddQuestionSuccess() throws QuestionAlreadyExistsException {
        when(questionRepository.save((Question) any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestion(question);
        Assert.assertEquals(question, savedQuestion);

        //verify here verifies that questionRepository save method is only called once
        verify(questionRepository, times(1)).save(question);
    }

    @Test
    public void saveQuestionTestFailure() throws QuestionAlreadyExistsException {
        when(questionRepository.save((Question) any())).thenReturn(null);
        Question savedQuestion = questionService.addQuestion(question);
        Assert.assertEquals(null,savedQuestion);
    }
}