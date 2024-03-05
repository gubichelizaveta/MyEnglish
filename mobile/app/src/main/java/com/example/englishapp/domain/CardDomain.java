package com.example.englishapp.domain;

public class CardDomain {
    private String Picture;
    private String Transcription;
    private String Translation;
    private String Word;
    public CardDomain()
    {

    }

    public CardDomain(String picture, String transcription, String translation, String word) {
        this.Picture = picture;
        this.Transcription = transcription;
        this.Translation = translation;
        this.Word = word;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        this.Picture = picture;
    }

    public String getTranscription() {
        return Transcription;
    }

    public void setTranscription(String transcription) {
        this.Transcription = transcription;
    }

    public String getTranslation() {
        return Translation;
    }

    public void setTranslation(String translation) {
        this.Translation = translation;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        this.Word = word;
    }
}
