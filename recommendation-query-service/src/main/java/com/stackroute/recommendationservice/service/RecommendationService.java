package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.QuestionRequested;
import com.stackroute.recommendationservice.model.User;

import java.util.List;

public interface RecommendationService {

    List<Question> getAllUnansweredQuestionsForRegisteredUser(String userName);

    List<QuestionRequested> getAllUnansweredQuestionsForGuestUser();

    QuestionRequested getDocumentByQuestionId(long questionId);

    List<User> getAllUsersRelatedToQuestion(long questionID);

    List<Question> getTrendingQuestionsForRegisteredUser(String username);

    List<QuestionRequested> getTrendingQuestionsForGuestUser();

    List<Question> getAllAcceptedAnswersOfDomain(String username);

}

