package com.stackroute.nlp.service;
import com.aliasi.sentences.IndoEuropeanSentenceModel;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.lang.reflect.Array;
import java.util.*;

public class NlpServiceImpl {


    String question;
    String stopwords[] = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "could", "he'd","above","below","be", "what","in","on","above",
            "is","i", "me", "my", "myself", "we", "our", "ours", "ourselves", "could", "he'd","!","@","#","$","%","^","&","*","()",".","?",
            "he'll", "he's", "here's", "how's", "ought", "she'd", "she'll", "that's", "there's", "they'd",
            "they'll", "they're", "they've", "we'd", "we'll", "we're", "we've", "what's", "when's", "where's",
            "who's", "why's", "would", "i'd", "i'll", "i'm", "i've", "you", "you're", "you've", "you'll",
            "you'd", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she",
            "she's", "her", "hers", "herself", "it", "it's", "its", "itself", "they", "them", "their",
            "theirs", "themselves", "who", "whom", "this", "that", "that'll", "these","what","why","which",};

    String[] domainSpecificTopics = {"Fundamentals and Architecture","pipes","Navigation","server Side","Using Promises","Http Client","Configuring Routes","Routing","Custom pipes","using pipes","Data Binding","Templates"};

    public static void main(String[] args) {

    }

    public void setquestion(String question) {
        this.question = question;
    }

    public String getCleanQuestion() {
        String inputsentence = this.question;
        // Data Cleaning by removing extra spaces.
        inputsentence = inputsentence.trim();
        inputsentence = inputsentence.replaceAll("\\s+", " ");
        inputsentence = inputsentence.replaceAll("\\t", " ");

        String[] tokenizedWord = inputsentence.split(" ");
        StringBuffer cleanquestion = new StringBuffer();
        for (int i = 0; i < tokenizedWord.length; i++) {
            cleanquestion.append(tokenizedWord[i] + " ");
        }
        return cleanquestion.toString().trim();
    }

    public ArrayList<String> getLemmitizedWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "lemma");
      //predefined properties of NLP Stanford
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        // This annotations object gives the special meaning to the
        // string we used in propeties.put() method
        Annotation annotations = new Annotation(getCleanQuestion());
        // pipeline.annotate(annotations)  provies the annotation to those particular objects.
        pipeline.annotate(annotations);
        // wordsList contains list of lemmetized words
        ArrayList<String> lemmaWords = new ArrayList<>();
        ArrayList<String> originalWords = new ArrayList<>();
        List<CoreMap> wordsList = annotations.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap words : wordsList) {
            for (CoreLabel word : words.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmaWords.add(word.lemma());
                originalWords.add(word.originalText());
            }
        }
        return lemmaWords;
    }

   //removal of stop words from the lemmitizedWords

    public ArrayList<String> getremoveStopWords() {
        ArrayList<String> removeStopwords = getLemmitizedWords();
        for (int i = 0; i < stopwords.length; i++) {
            if (removeStopwords.contains(stopwords[i])) {
                removeStopwords.remove(stopwords[i]);
            }
        }
        return removeStopwords;
    }

    //removal of stop words from the sentence

    public String getSentenceWithoutStopWords() {
        ArrayList<String> removeStopwords = getremoveStopWords();
        StringBuffer sentenceWithoutStopWords = new StringBuffer();
        for (int i = 0; i < removeStopwords.size(); i++) {
            sentenceWithoutStopWords.append(removeStopwords.get(i) + " ");
        }
        return sentenceWithoutStopWords.toString().trim();
    }


    public void showAllResults() {
        System.out.println("Get Cleared Paragraph");
        String clearedParagraph = new String(getCleanQuestion());
        System.out.println(clearedParagraph);

        System.out.println("Lemmitization");
        ArrayList<String> allLemmas = new ArrayList<>(getLemmitizedWords());
        System.out.println(allLemmas);

        System.out.println("Stop Word Removal");
        ArrayList<String> allStopWords = new ArrayList<>(getremoveStopWords());
        System.out.println(allStopWords);

        System.out.println("Stop Word Removal from sentence");
        String allremovedWords = new String(getSentenceWithoutStopWords());
        System.out.println(allremovedWords);

    }


}


