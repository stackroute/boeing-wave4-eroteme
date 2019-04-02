
package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Question;
import com.stackroute.searchservice.domain.Topic;
import com.stackroute.searchservice.exceptions.ConceptNotFoundException;
import com.stackroute.searchservice.model.QuestionDTO;

import java.util.List;

public interface SearchService {
    void saveTopic(Topic topic);

    List<Topic> getAllTopics() throws ConceptNotFoundException;

    List<Topic> getQuestionsByTopic(String topic);

    List<Topic> getQuestion(List<String> topic) throws ConceptNotFoundException;

    String questionOfPost(QuestionDTO question);

    List<Question> getQuestionInside(List<String> topic, String question) throws ConceptNotFoundException;


}



