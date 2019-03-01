package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.QuestionDocumentRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationServiceImplTest {
    private static final String USERNAME = "USERNAME";
    private static final User USER = User.builder().username(USERNAME).reputation(234).build();
    private static final int QUESTION_ID = 10;
    private static final Question QUESTION = Question.builder().questionId(QUESTION_ID)
            .question("").timestamp(213).upvote(12431).downvote(12).build();
    @Mock
    private UserRepository userRepository;
    @Mock
    private QuestionDocumentRepository questionDocumentRepository;
    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Test
    public void testForTrendingQuestionsPresentInDb() {
        when(userRepository.getAllTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION));
        assertThat(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).containsOnly(QUESTION);
    }

    @Test
    public void testForTrendingQuestionsNotPresentInDb() {
        when(userRepository.getAllTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).isEmpty();
    }

    @Test
    public void testForUnansweredQuestionsPresentInDb() {
        when(userRepository.findAllUnansweredQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION));
        assertThat(recommendationService.getAllUnansweredQuestionsForRegisteredUser(USERNAME)).containsExactly(QUESTION);
    }

    @Test
    public void testForUnansweredQuestionsNotPresentInDb() {
        when(userRepository.findAllUnansweredQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getAllUnansweredQuestionsForRegisteredUser(USERNAME)).isEmpty();
    }

    @Test
    public void testForEligibleUsersNotPresentInDb() {
        when(userRepository.findAllUsersRelatedToTopic(QUESTION_ID)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).isEmpty();
    }

    @Test
    public void testForEligibleUsersPresentInDb() {
        when(userRepository.findAllUsersRelatedToTopic(QUESTION_ID)).thenReturn(Collections.singletonList(USER));
        assertThat(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).containsExactly(USER);
    }
}