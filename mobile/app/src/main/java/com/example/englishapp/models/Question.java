package com.example.englishapp.models;

public class Question {
    private String ID;
    public String QuestionText;
    public String CorrectAnswer;
    public String AnswerVariant1;
    public String AnswerVariant2;
    public String AnswerVariant3;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public String getAnswerVariant1() {
        return AnswerVariant1;
    }

    public void setAnswerVariant1(String answerVariant1) {
        AnswerVariant1 = answerVariant1;
    }

    public String getAnswerVariant2() {
        return AnswerVariant2;
    }

    public void setAnswerVariant2(String answerVariant2) {
        AnswerVariant2 = answerVariant2;
    }

    public String getAnswerVariant3() {
        return AnswerVariant3;
    }

    public void setAnswerVariant3(String answerVariant3) {
        AnswerVariant3 = answerVariant3;
    }
}
