package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.repository.RecommendationRepository;
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
    private static final String USER = "USER";
    private static final String TOPIC = "TOPIC";
    private static final int QUESTION_ID = 10;
    private static final Question QUESTION = Question.builder().questionId(QUESTION_ID).build();
    @Mock
    private UserRepository userRepository;
    @Mock
    private RecommendationRepository recommendationRepository;
    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Test
    public void testForTrendingQuestionsPresentInDb() {
        when(userRepository.getAllTrendingQuestionsForUser(USER, TOPIC)).thenReturn(Collections.singletonList(QUESTION));
        assertThat(recommendationService.getTrendingQuestionsForUser(USER, TOPIC)).containsOnly(QUESTION);
    }

    @Test
    public void testForTrendingQuestionsNotPresentInDb() {
        when(userRepository.getAllTrendingQuestionsForUser(USER, TOPIC)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getTrendingQuestionsForUser(USER, TOPIC)).isEmpty();
    }
}