package com.example.englishapp.models;


public class Card {
    private String Word;
    private String Picture;
    private String Translation;
    private String Transcription;
    //private Module ModuleTitle;
    private String ModuleTitle;

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getTranslation() {
        return Translation;
    }

    public void setTranslation(String translation) {
        Translation = translation;
    }

    public String getTranscription() {
        return Transcription;
    }

    public void setTranscription(String transcription) {
        Transcription = transcription;
    }

    public String getModule() {
        return ModuleTitle;
    }

    public void setModule(com.example.englishapp.models.Module module) {
        ModuleTitle = String.valueOf(module);
    }
}
