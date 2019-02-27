//package com.stackroute.repository;
//
//import com.stackroute.domain.Question;
//import com.stackroute.domain.User;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
////JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
//@RunWith(SpringRunner.class)
//
//// Used when a test focuses only on MongoDB components.
//@DataMongoTest
//public class QuestionRepositoryTest {
//
//    @Autowired
//    private QuestionRepository questionRepository;
//    private Question question;
//
//    @Before
//    public void setUp() throws Exception {
//        User user = new User("grace@gmail.com","Grace","https://previews.123rf.com/images/joloei/joloei1701/joloei170100028/70835595-letter-g-alphabet-with-damask-rose-abc-concept-type-as-logo-isolated-on-white-background.jpg");
//        List<String> topics = new ArrayList<>();
//        topics.add("Angular");
//        question = new Question(77, "What is the basics of angular?", null,topics, 0, 4578533, 0, user, null, null);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        questionRepository.deleteAll();
//    }
//
//    @Test
//    public void testSaveQuestionSuccess(){
//        questionRepository.save(question);
//        Question fetchQuestion = questionRepository.findById(question.getQuestionId()).get();
//        Assert.assertEquals(77,fetchQuestion.getQuestionId());
//    }
//
//    @Test
//    public void testSaveQuestionFailure(){
//        User user = new User("graceanjo@gmail.com","Anjo Grace","https://previews.123rf.com/images/joloei/joloei1701/joloei170100028/70835595-letter-g-alphabet-with-damask-rose-abc-concept-type-as-logo-isolated-on-white-background.jpg");
//        List<String> topics = new ArrayList<>();
//        topics.add("Angular");
//        Question testQuestion = new Question(79, "What is the basics of angular?", null,topics, 0, 4578533, 0, user, null, null);
//        questionRepository.save(question);
//        Question fetchQuestion = questionRepository.findById(question.getQuestionId()).get();
//        Assert.assertNotSame(testQuestion,fetchQuestion);
//    }
//
//    @Test
//    public void testExistsByQuestionSuccess(){
//        questionRepository.save(question);
//        boolean questionExists = questionRepository.existsByQuestion(question.getQuestion());
//        Assert.assertTrue(questionExists);
//    }
//
//    @Test
//    public void testExistsByQuestionFailure(){
//        boolean questionExists = questionRepository.existsByQuestion(question.getQuestion());
//        Assert.assertFalse(questionExists);
//    }
//
//    @Test
//    public void testFindByQuestionIdSuccess(){
//        questionRepository.save(question);
//        Question fetchQuestion = questionRepository.findByQuestionId(question.getQuestionId());
//        Assert.assertEquals(question,fetchQuestion);
//    }
//
//    @Test
//    public void testFindByQuestionIdFailure(){
//        Question fetchQuestion = questionRepository.findByQuestionId(question.getQuestionId());
//        Assert.assertNull(fetchQuestion);
//    }
//}