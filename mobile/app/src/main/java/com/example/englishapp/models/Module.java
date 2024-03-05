package com.example.englishapp.models;

import java.util.List;

public class Module {
    private String Title;
    private List<Card> Cards;
    private List<Test> Tests;
    //private List<User> Users;

    public String getModuleTitle() {
        return Title;
    }

    public void setModuleTitle(String moduleTitle) {
        Title = moduleTitle;
    }

    public List<Card> getCards() {
        return Cards;
    }

    public void setCards(List<Card> cards) {
        Cards = cards;
    }

    public List<Test> getTests() {
        return Tests;
    }

    public void setTests(List<Test> tests) {
        Tests = tests;
    }

//    public List<User> getUsers() {
//        return Users;
//    }
//
//    public void setUsers(List<User> users) {
//        Users = users;
//    }
}
