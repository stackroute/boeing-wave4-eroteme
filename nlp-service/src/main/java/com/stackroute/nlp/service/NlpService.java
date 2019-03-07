package com.stackroute.nlp.service;

import com.stackroute.nlp.domain.Nlp;
import com.stackroute.nlp.exceptions.QuestionNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface NlpService {
    public String setquestion(String question) throws QuestionNotFoundException;
    public List<String> getLemmitizedWords();
    public List<String> getRemoveStopWords();
    public String getSentenceWithoutStopWords();
    public List<String> getDomainSpecificTopicName();
    public String getCleanQuestion();
    public List<Nlp> getPOSWords();
}
