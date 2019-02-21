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

import java.util.*;

public class NlpServiceImpl {


    String question;
    String stopwords[] = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "could", "he'd",
            "she's", "her", "hers", "herself", "it", "it's", "its", "itself", "they", "them", "their","is"};

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
        String[] tokenWord = inputsentence.split(" ");
        StringBuffer cleanedquestion = new StringBuffer();
        for (int i = 0; i < tokenWord.length; i++) {
            cleanedquestion.append(tokenWord[i] + " ");
        }
        return cleanedquestion.toString().trim();
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
        // sentenceList contains list of sentences
        ArrayList<String> lemmaWords = new ArrayList<>();
        ArrayList<String> originalWords = new ArrayList<>();
        List<CoreMap> sentenceList = annotations.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentenceList) {
            for (CoreLabel word : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmaWords.add(word.lemma());
                originalWords.add(word.originalText());
            }
        }
        return lemmaWords;
    }



    public void showAllResults() {
        System.out.println("Get Cleared Paragraph");
        String clearedParagraph = new String(getCleanQuestion());
        System.out.println(clearedParagraph);

        System.out.println("Lemmitization");
        ArrayList<String> allLemmas = new ArrayList<>(getLemmitizedWords());
        System.out.println(allLemmas);


    }

}


