package com.example.englishapp.domain;

public class TestDomain {
    private String TestId;
    private String CorrectAnswer;
    private String QuestionText;
    private String AnswerVariant1;
    private String AnswerVariant2;
    private String AnswerVariant3;


    public TestDomain() {
    }

    public TestDomain(String answerVariant1, String answerVariant2, String answerVariant3, String correctAnswer, String questionText) {
        AnswerVariant1 = answerVariant1;
        AnswerVariant2 = answerVariant2;
        AnswerVariant3 = answerVariant3;
        CorrectAnswer = correctAnswer;
        QuestionText = questionText;
    }
    public String getTestId() {
        return TestId;
    }

    public void setTestId(String testId) {
        TestId = testId;
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

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }
}
