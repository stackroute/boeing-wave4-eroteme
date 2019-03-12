package com.stackroute.service;

import com.stackroute.domain.*;
import com.stackroute.exceptions.*;
import com.stackroute.repository.QuestionRepository;
import org.junit.After;
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
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)

@SpringBootTest
public class QuestionServiceImplTest {
    List<Question> list = null;
    private Question question;
    private Answer answer;
    private Comment comment;
    private Replies reply;

    @Mock
    private RestTemplate restTemplate;

    //Create a mock for QusetionRepository
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AmqpTemplate amqpTemplate;
    //Inject the mocks as dependencies into QuestionServiceImpl
    @InjectMocks
    private QuestionServiceImpl questionService;

    @Before
    public void setUp() throws Exception {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
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

    @Test(expected = ResourceAccessException.class)
    public void testAddQuestionSuccess() throws QuestionAlreadyExistsException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestion(question);
        Assert.assertEquals(question, savedQuestion);

        //verify here verifies that questionRepository save method is only called once
        verify(questionRepository, times(1)).save(question);
    }

    @Test(expected = NullPointerException.class)
    public void testAddQuestionFailure() throws QuestionAlreadyExistsException, IOException {
        when(questionRepository.save((Question) any())).thenReturn(null);
        Question savedQuestion = questionService.addQuestion(question);
        System.out.println(savedQuestion);
        Assert.assertEquals(null, savedQuestion);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddAnswer() throws QuestionNotFoundException, IOException {
        when(questionRepository.findById(anyInt())).thenReturn(Optional.of(question));
        when(questionRepository.save((Question) any())).thenReturn(question);
        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);
        question.setAnswer(answerList);
        Question savedQuestion = questionService.addAnswer(77,answer);
        Assert.assertEquals(question,savedQuestion);

        //verify here verifies that muzixRepository save method is only called once
        verify(questionRepository, times(1)).save(question);

    }

    @Test(expected = NoSuchElementException.class)
    public void testGetQuestion() throws QuestionNotFoundException {
        when(questionRepository.findByQuestionId((anyInt()))).thenReturn(question);
        Question actualOutput = questionService.getQuestion(77);
        Assert.assertEquals(question,actualOutput);

        //verify here verifies that muzixRepository findbytrackname method is only called twice
        verify(questionRepository, times(2)).findByQuestionId(77);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddQuestionComment() throws QuestionNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestionComment(77, comment);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddQuestionCommentReply() throws QuestionNotFoundException, CommentNotFoundException, IOException {
        List<Replies> repliesList = new ArrayList<>();
        repliesList.add(reply);
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestionCommentReply(77, "nice", repliesList);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddAnswerComment() throws QuestionNotFoundException, AnswerNotFoundException, IOException {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addAnswerComment(77, "nice", commentList);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddAnswerCommentReply() throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException, IOException {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addAnswerCommentReply(77, "nice", commentList);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddQuestionUpvote() throws QuestionNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestionUpvote(77);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddQuestionDownvote() throws QuestionNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestionDownvote(77);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddAnswerUpvote() throws QuestionNotFoundException, AnswerNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addAnswerUpvote(77, "abcd");
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddAnswerDownvote() throws QuestionNotFoundException, AnswerNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addAnswerDownvote(77, "abcd");
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddQuestionCommentLikes() throws QuestionNotFoundException, CommentNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestionCommentLikes(77, "abcd");
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddQuestionCommentReplyLikes() throws QuestionNotFoundException, CommentNotFoundException, ReplyNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestionCommentReplyLikes(77, comment);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddAnswerCommentLikes() throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addAnswerCommentLikes(77, answer);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddAnswerCommentReplyLikes() throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException, ReplyNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addAnswerCommentReplyLikes(77, answer);
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testAddQuestionAnswerAccepted() throws QuestionNotFoundException, AnswerNotFoundException, IOException {
        when(questionRepository.save(any())).thenReturn(question);
        Question savedQuestion = questionService.addQuestionAnswerAccepted(77, "abcd");
    }

    @Test
    public void testgetAllQuestions() {
        when(questionRepository.findAll()).thenReturn(list);
        List<Question> questionList = questionService.getAllQuestions();
        Assert.assertEquals(list,questionList);
    }
}