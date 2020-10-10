package com.ashiqurrahman.dictionary;

public class DictionaryObject {
    private String antonym;
    private String meaning;
    private String sentence;
    private String synonym;
    private String word;

    public DictionaryObject(String word, String meaning, String synonym, String antonym, String sentence) {
        this.word = word;
        this.meaning = meaning;
        this.antonym = antonym;
        this.sentence = sentence;
        this.synonym = synonym;
    }

    public String getWord() {
        return this.word;
    }

    public String getSynonym() {
        return this.synonym;
    }

    public String getAntonym() {
        return this.antonym;
    }

    public String getSentence() {
        return this.sentence;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
