package com.stackroute.recommendationcommandservice.service;


import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;

import java.util.Collection;


public interface RecommendationCommandService {


    User saveUserToDb(User user);


    Collection<User> getUsers();


    Question saveQuestionToDb(Question question);


    Collection<Question> getQuestions();


    Answer saveAnswerToDb(Answer answer);


    Collection<Answer> getAnswers();


//    void deleteAnswers(long answerId);


    User userfollowstopic(String userName, String Name);


    User getByUser(int reputation);


    Question questionbelongstopic(int questionId, String Name);


    User useransweredanswer(String userName, int answerId);


    User userviewedquestion(String userName, int questionId);


    Answer answerisanswerofquestion(int answerId, int questionId);


    User useraskedquestion(String userName, int questionId);


    User useracceptedanswer(String userName, int answerId);


    User userupvotedanswer(String userName, int answerId);


    User userdownvotedanswer(String userName, int answerId);


    User userupvotequestion(String userName, int questionId);

    User userdownvotequestion(String userName, int questionId);

}
