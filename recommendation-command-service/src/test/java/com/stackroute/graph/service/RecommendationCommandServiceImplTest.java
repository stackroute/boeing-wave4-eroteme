package com.stackroute.graph.service;

import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.Question;
import com.stackroute.graph.model.User;
import com.stackroute.graph.repository.AnswerRepository;
import com.stackroute.graph.repository.QuestionRepository;
import com.stackroute.graph.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationCommandServiceImplTest {

    private static final User USER = User.builder().userName("kate").reputation(1).build();
    private static final Question QUESTION = Question.builder().questionId(12).questionString("what is java").timestamp(34544).upVote(32).downVote(1).build();
    private static final Answer ANSWER = Answer.builder().answerId(21).answerString("Dont do it").build();




    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private RecommendationCommandServiceImpl recommendationCommandService;


    @Test
    public void saveUserToDb() {
        when(userRepository.save(USER)).thenReturn(USER);
        assertThat(recommendationCommandService.saveUserToDb(USER)).isEqualTo(USER);
    }


    @Test
    public void getUsers() {

        when(userRepository.getAllUsers()).thenReturn(Collections.singletonList(USER));
        assertThat(recommendationCommandService.getUsers()).containsOnly(USER);

    }



    @Test
    public void saveQuestionToDb() {
        when(questionRepository.save(QUESTION)).thenReturn(QUESTION);
        assertThat(recommendationCommandService.saveQuestionToDb(QUESTION)).isEqualTo(QUESTION);

    }


    @Test
    public void getQuestions() {

        when(questionRepository.getAllQuestions()).thenReturn(Collections.singletonList(QUESTION));
        assertThat(recommendationCommandService.getQuestions()).containsOnly(QUESTION);

    }


    @Test
    public void saveAnswerToDb() {
        when(answerRepository.save(ANSWER)).thenReturn(ANSWER);
        assertThat(recommendationCommandService.saveAnswerToDb(ANSWER)).isEqualTo(ANSWER);

    }

    @Test
    public void getAnswers() {
        when(answerRepository.getAllAnswers()).thenReturn(Collections.singletonList(ANSWER));
        assertThat(recommendationCommandService.getAnswers()).containsOnly(ANSWER);

    }

    @Test
    public void userfollowstopic() {

        when(userRepository.userfollowstopicrelationship("meghana", "testing")).thenReturn(USER);
        assertThat(recommendationCommandService.userfollowstopic("meghana", "testing")).isEqualTo(USER);

    }

    @Test
    public void getByUser() {

        when(userRepository.getAllUsers()).thenReturn(Collections.singletonList(USER));
        assertThat(recommendationCommandService.getUsers()).containsOnly(USER);

    }

    @Test
    public void questionbelongstopic() {
        when(questionRepository.questionbelongstopicrelationship(12, "pipes")).thenReturn(QUESTION);
        assertThat(recommendationCommandService.questionbelongstopic(12, "pipes")).isEqualTo(QUESTION);

    }

    @Test
    public void useransweredanswer() {
        when(answerRepository.useransweredanswerrelationship("srinidhi", 201)).thenReturn(USER);
        assertThat(recommendationCommandService.useransweredanswer("srinidhi", 201)).isEqualTo(USER);

    }

    //
    @Test
    public void userviewedquestion() {
        when(questionRepository.userviewedquestionrelationship("anirudh", 101)).thenReturn(USER);
        assertThat(recommendationCommandService.userviewedquestion("anirudh", 101)).isEqualTo(USER);

    }

    @Test
    public void answerisanswerofquestion() {
        when(questionRepository.answerisanswerofquestionrelationship(202, 101)).thenReturn(ANSWER);
        assertThat(recommendationCommandService.answerisanswerofquestion(202, 101)).isEqualTo(ANSWER);

    }

    @Test
    public void useraskedquestion() {
        when(questionRepository.useraskedquestionrelationship("kiran", 101)).thenReturn(USER);
        assertThat(recommendationCommandService.useraskedquestion("kiran", 101)).isEqualTo(USER);


    }

    @Test
    public void useracceptedanswer() {
        when(answerRepository.useracceptedanswerrelationship("gagana", 201)).thenReturn(USER);
        assertThat(recommendationCommandService.useracceptedanswer("gagana", 201)).isEqualTo(USER);

    }

    @Test
    public void userupvotedanswer() {
        when(answerRepository.userupvotedanswerrelationship("varun", 201)).thenReturn(USER);
        assertThat(recommendationCommandService.userupvotedanswer("varun", 201)).isEqualTo(USER);

    }

    @Test
    public void userdownvotedanswer() {
        when(answerRepository.userdownvotedanswerrelationship("harsha Bean", 222)).thenReturn(USER);
        assertThat(recommendationCommandService.userdownvotedanswer("harsha Bean", 222)).isEqualTo(USER);

    }

    @Test
    public void userupvotequestion() {
        when(questionRepository.userupvotequestionrelationship("easwar", 2696)).thenReturn(USER);
        assertThat(recommendationCommandService.userupvotequestion("easwar", 2696)).isEqualTo(USER);

    }

    @Test
    public void userdownvotequestion() {

        when(questionRepository.userdownvotequestionrelationship("siddharth", 102)).thenReturn(USER);
        assertThat(recommendationCommandService.userdownvotequestion("siddharth", 102)).isEqualTo(USER);

    }
}