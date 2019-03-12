package com.stackroute.nlp.service;

import com.stackroute.nlp.exceptions.QuestionNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest
public class NlpServiceImplTest {

    @Mock
    AmqpTemplate amqpTemplate;
    @InjectMocks
    NlpServiceImpl nlpService;
    private String question;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        question = "what is angular";
    }

    @Test
    public void testSetquestionSuccess() throws QuestionNotFoundException {
        String savedQuestion = nlpService.setquestion(question);
        Assert.assertEquals(question, savedQuestion);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testSetquestionFailure() throws QuestionNotFoundException {
        String savedQuestion = nlpService.setquestion(null);
        Assert.assertEquals(question, savedQuestion);
    }
}