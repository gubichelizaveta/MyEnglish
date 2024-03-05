package com.example.englishapp.domain;

public class ProgressDomain {
    private String Mark;
    private String Count;

    public ProgressDomain() {
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public ProgressDomain(String mark, String count) {
        Mark = mark;
        Count = count;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        Mark = mark;
    }
}
