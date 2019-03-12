package com.stackroute.recommendationcommandservice.service;


import com.stackroute.recommendationcommandservice.model.AnswerNode;
import com.stackroute.recommendationcommandservice.model.QuestionNode;
import com.stackroute.recommendationcommandservice.model.UserNode;

import java.util.Collection;
import java.util.List;


public interface RecommendationCommandService {


    UserNode saveUserToDb(UserNode userNode);


    Collection<UserNode> getUsers();


    QuestionNode saveQuestionToDb(QuestionNode questionNode);


    Collection<QuestionNode> getQuestions();


    AnswerNode saveAnswerToDb(AnswerNode answerNode);


    Collection<AnswerNode> getAnswers();


    UserNode userFollowsTopic(String userName, List<String> Name);


    UserNode getByUser(int reputation);


    QuestionNode questionBelongsTopic(int questionId, List<String> Name);


    UserNode userAnsweredAnswer(String userName, String answerString);


    UserNode userViewedQuestion(String userName, int questionId);


    AnswerNode answerIsAnswerOfQuestion(String answerString, int questionId);


    UserNode userAskedQuestion(String userName, int questionId);


    UserNode userAcceptedAnswer(String userName, String answerString);


    UserNode userUpvotedAnswer(String userName, String answerString);


    UserNode userDownvotedAnswer(String userName, String answerString);


    UserNode userUpvoteQuestion(String userName, int questionId);

    UserNode userDownvoteQuestion(String userName, int questionId);

}
