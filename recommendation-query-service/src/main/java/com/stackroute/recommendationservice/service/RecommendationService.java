package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.AnswerRequested;
import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;

import java.util.List;

public interface RecommendationService {

    List<Question> getAllUnansweredQuestions(String userName);

    QuestionRequested getDocumentByQuestionId(long questionId);

    List<User> getAllUsersRelatedToQuestion(long questionID);

    List<Question> getTrendingQuestionsForUser(String username);

    List<AnswerRequested> getAllAcceptedAnswerOfDomain(String userName);
}

