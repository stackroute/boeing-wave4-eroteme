package com.stackroute.nlp.service;

public class NLP {
    private String originalWord;

    @Override
    public String toString() {
        return "NLP{" +
                "originalWord='" + originalWord + '\'' +
                ", partsOfSpeech='" + partsOfSpeech + '\'' +
                '}';
    }

    private String partsOfSpeech;

    public String getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }

    public String getPartsOfSpeech() {
        return partsOfSpeech;
    }

    public void setPartsOfSpeech(String partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public NLP(String originalWord, String partsOfSpeech) {
        this.originalWord = originalWord;
        this.partsOfSpeech = partsOfSpeech;
    }

    public NLP() {
    }

}