package com.stackroute.nlp.service;

import com.stackroute.nlp.model.NLP;

import java.util.ArrayList;
import java.util.List;

public interface NlpService {
    public String setquestion(String question) ;
    public ArrayList<String> getLemmitizedWords();
    public ArrayList<String> getremoveStopWords();
    public String getSentenceWithoutStopWords();
    public List<String> getDomainSpecificTopicName();
    public String getCleanQuestion();
    public ArrayList<NLP> getPOSWords();
}
