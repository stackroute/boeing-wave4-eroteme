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
    String stopwords[] = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "could", "he'd","above","below",
            "is"};

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
        StringBuffer cleanedquestion = new StringBuffer();
        for (int i = 0; i < tokenizedWord.length; i++) {
            cleanedquestion.append(tokenizedWord[i] + " ");
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
//
//    public List<String> getStemmedWords() {
//        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
//        TokenizerFactory porterFactory = new PorterStemmerTokenizerFactory(tokenizerFactory);
//        ArrayList<String> wordTokens = getLemmitizedWords();
//        List<String> stemmedWordsList = new ArrayList<>();
//        for (String word : wordTokens) {
//            Tokenization tokenization = new Tokenization(word, porterFactory);
//            stemmedWordsList.add(tokenization.tokenList().toString());
//        }
//        return stemmedWordsList;
//    }

    public ArrayList<String> getWordsWithoutStopWords() {
        ArrayList<String> wordsWithOutStopwords = getLemmitizedWords();
        for (int i = 0; i < stopwords.length; i++) {
            if (wordsWithOutStopwords.contains(stopwords[i])) {
                wordsWithOutStopwords.remove(stopwords[i]);//remove it
            }
        }
        return wordsWithOutStopwords;
    }

    public String getSentenceWithOutStopWords() {
        ArrayList<String> wordsWithOutStopwords = getWordsWithoutStopWords();
        StringBuffer sentenceWithOutStopWords = new StringBuffer();
        for (int i = 0; i < wordsWithOutStopwords.size(); i++) {
            sentenceWithOutStopWords.append(wordsWithOutStopwords.get(i) + " ");
        }
        return sentenceWithOutStopWords.toString().trim();
    }

    public ArrayList<NLP> getPOSWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument coreDocument = new CoreDocument(getSentenceWithOutStopWords());
        pipeline.annotate(coreDocument);
        List<CoreLabel> coreLabelsList = coreDocument.tokens();
        ArrayList<NLP> wordsWithPOSTag = new ArrayList<>();
        for (CoreLabel coreLabel : coreLabelsList) {
            String partsOfSpeech = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            wordsWithPOSTag.add(new NLP(coreLabel.originalText(), partsOfSpeech));
        }
        return wordsWithPOSTag;
    }


    public void showAllResults() {
        System.out.println("Get Cleared Paragraph");
        String clearedParagraph = new String(getCleanQuestion());
        System.out.println(clearedParagraph);

        System.out.println("Lemmitization");
        ArrayList<String> allLemmas = new ArrayList<>(getLemmitizedWords());
        System.out.println(allLemmas);

//        System.out.println("Stemming");
//        ArrayList<String> allStems = new ArrayList<>(getStemmedWords());
//        System.out.println(allStems);

        System.out.println("Stop Word Removal");
        ArrayList<String> allStopWords = new ArrayList<>(getWordsWithoutStopWords());
        System.out.println(allStopWords);

        System.out.println("Stop Word Removal Paragraph");
        String sentenceWithOutStopWords = new String(getSentenceWithOutStopWords());
        System.out.println(sentenceWithOutStopWords);

        System.out.println("POS TAGGING");
        ArrayList<NLP> nlp = new ArrayList<>(getPOSWords());
        System.out.println(nlp);
    }


}


