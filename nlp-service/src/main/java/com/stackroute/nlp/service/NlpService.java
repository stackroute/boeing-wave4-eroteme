package com.stackroute.nlp.service;

import com.stackroute.nlp.domain.Nlp;
import com.stackroute.nlp.exceptions.QuestionNotFoundException;

import java.util.List;

public interface NlpService {
    String setquestion(String question) throws QuestionNotFoundException;

    List<String> getLemmitizedWords();

    List<String> getRemoveStopWords();

    String getSentenceWithoutStopWords();

    List<String> getDomainSpecificTopicName();

    String getCleanQuestion();

    List<Nlp> getPOSWords();
}
