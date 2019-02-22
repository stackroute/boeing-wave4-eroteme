package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {

    List<Question> getAllUnansweredQuestions(String userName);

    QuestionRequested getDocumentByQuestionId(long questionId);

    List<User> getAllUsers();

    List<Question> getTrendingQuestionsForUser(String s, String username);


}

