package com.stackroute.nlp.service;

import com.stackroute.nlp.domain.Nlp;
import com.stackroute.nlp.exceptions.QuestionNotFoundException;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NlpServiceImpl implements NlpService {

    @Value("${jsb.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsb.rabbitmq.routingkey}")
    private String routingKey;

    private static final Logger log = LoggerFactory.getLogger(NlpServiceImpl.class);
    String question;
    @Value("${stopwords}")
String[] stopwords;

    ArrayList<String> domainSpecificTopics = new ArrayList<>(Arrays.asList("pipes", "Fundamentals and Architecture", "Navigation", "server Side", "Using Promises", "Http Client", "Configuring Routes", "Routing", "Custom pipes", "using pipes", "Data Binding", "Templates", "angular"));
    @Autowired
    private AmqpTemplate amqpTemplate;

    public String setquestion(String question) throws QuestionNotFoundException {
        if (question == null) {
            throw new QuestionNotFoundException();
        }
        this.question = question;
        getDomainSpecificTopicName();
        return question;
    }

    @Override
    public String getCleanQuestion() {
        String inputsentence = this.question;
        // Data Cleaning by removing extra spaces.
        inputsentence = inputsentence.trim();
        inputsentence = inputsentence.replaceAll("-", " ");
        inputsentence = inputsentence.replaceAll(",", " ");
        inputsentence = inputsentence.replaceAll("\\s+", " ");
        inputsentence = inputsentence.replaceAll("\\t", " ");
        System.out.println("inputsentence = ");
        System.out.println(inputsentence);
        return inputsentence.trim();
    }


    //removal of stop words from the lemmitizedWords
    @Override
    public List<String> getLemmitizedWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "lemma");
        //predefined properties of Nlp Stanford
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

    //removal of stop words from the sentence
    @Override
    public List<String> getRemoveStopWords() {
        List<String> removeStopwords = getLemmitizedWords();
        for (int i = 0; i < stopwords.length; i++) {
            removeStopwords.remove(stopwords[i]);
        }
        return removeStopwords;
    }

    //Removal of stopwords from the sentence
    @Override
    public String getSentenceWithoutStopWords() {
        List<String> removeStopwords = getRemoveStopWords();
        StringBuilder sentenceWithoutStopWords = new StringBuilder();
        for (int i = 0; i < removeStopwords.size(); i++) {
            sentenceWithoutStopWords.append(removeStopwords.get(i) + " ");
        }
        return sentenceWithoutStopWords.toString().trim();
    }

    @Override
    //List of parts of speech words
    public ArrayList<Nlp> getPOSWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument coreDocument = new CoreDocument(getSentenceWithoutStopWords());
        pipeline.annotate(coreDocument);
        List<CoreLabel> coreLabelsList = coreDocument.tokens();
        ArrayList<Nlp> wordsWithPOSTag = new ArrayList<>();
        for (CoreLabel coreLabel : coreLabelsList) {

            String partsOfSpeech = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if (domainSpecificTopics.contains(coreLabel.originalText())) {
                wordsWithPOSTag.add(new Nlp(coreLabel.originalText(), "NN"));
            } else {
                wordsWithPOSTag.add(new Nlp(coreLabel.originalText(), partsOfSpeech));
            }
        }
        return wordsWithPOSTag;
    }


    public String getLemmitizedWord(String conceptName) {
        Properties properties = new Properties();
        properties.setProperty("annotator", "lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        Annotation annotations = new Annotation(conceptName);
        pipeline.annotate(annotations);
        StringBuilder lemmitizedConcetName = new StringBuilder();
        List<CoreMap> wordsList = annotations.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap words : wordsList) {
            for (CoreLabel word : words.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmitizedConcetName.append(word.lemma() + " ");
            }
        }
        return lemmitizedConcetName.toString().trim();
    }

    @Override
    //  finding the DomainSpecificTopicnames in the given question
    public List<String> getDomainSpecificTopicName() {
        String defaultConceptName = "angular";
        String sentenceWithoutStopWords = getSentenceWithoutStopWords();
        List<String> conceptNameList = new ArrayList<>();
        for (int i = 0; i < domainSpecificTopics.size(); i++) {
            String pattenString = domainSpecificTopics.get(i).toLowerCase();
            Pattern pattern = Pattern.compile(pattenString);
            Matcher matcher = pattern.matcher(sentenceWithoutStopWords.toLowerCase());
            if (matcher.find()) {
                conceptNameList.add(domainSpecificTopics.get(i).toLowerCase());
                log.info(domainSpecificTopics.get(i).toLowerCase());
            } else {
                String originalDomainName = domainSpecificTopics.get(i).toLowerCase();
                String modifiedDomainName = originalDomainName.replaceAll(" ", "");
                pattenString = modifiedDomainName;
                pattern = Pattern.compile(pattenString);
                matcher = pattern.matcher(sentenceWithoutStopWords.toLowerCase());
                if (matcher.find()) {
                    conceptNameList.add(domainSpecificTopics.get(i).toLowerCase());
                    log.info(domainSpecificTopics.get(i).toLowerCase());
                } else {
                    pattenString = getLemmitizedWord(domainSpecificTopics.get(i).toLowerCase());
                    pattern = Pattern.compile(pattenString);
                    matcher = pattern.matcher(sentenceWithoutStopWords.toLowerCase());
                    if (matcher.find()) {
                        conceptNameList.add(domainSpecificTopics.get(i).toLowerCase());
                        log.info(domainSpecificTopics.get(i).toLowerCase());
                    } else {
                        originalDomainName = domainSpecificTopics.get(i).toLowerCase();
                        modifiedDomainName = getLemmitizedWord(originalDomainName.replaceAll(" ", ""));
                        pattenString = modifiedDomainName;
                        pattern = Pattern.compile(pattenString);
                        matcher = pattern.matcher(sentenceWithoutStopWords.toLowerCase());
                        if (matcher.find()) {
                            conceptNameList.add(domainSpecificTopics.get(i).toLowerCase());
                            log.info(domainSpecificTopics.get(i).toLowerCase());
                        }
                    }
                }
            }
        }
        if (conceptNameList == null || conceptNameList.size() == 0) {

            conceptNameList.add(defaultConceptName);
        }
        log.info(String.valueOf(conceptNameList));
        produceMsg(conceptNameList);
        return conceptNameList;
    }


    // RabbitMq message producer method
    public void produceMsg(List<String> msg) {
        log.info("Sending message");
        log.info("Send msg = " + msg);
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        log.info("Send msg = " + msg);
    }
}
