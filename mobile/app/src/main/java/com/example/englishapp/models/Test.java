package com.example.englishapp.models;

import java.util.List;

public class Test {
    private String ID;
    private List<Question> Questions;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        Questions = questions;
    }
}
