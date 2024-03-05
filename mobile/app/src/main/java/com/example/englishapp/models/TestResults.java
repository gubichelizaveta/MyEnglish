package com.example.englishapp.models;

public class TestResults {
    private int TestID;
    private String UsersName;
    private int Mark;

    public int getTestID() {
        return TestID;
    }

    public int getMark() {
        return Mark;
    }

    public String getUsersName() {
        return UsersName;
    }

    public void setTestID(int testID) {
        TestID = testID;
    }

    public void setMark(int mark) {
        Mark = mark;
    }

    public void setUsersName(String usersName) {
        UsersName = usersName;
    }

    public TestResults(int testID, String usersName,int mark) {
        TestID = testID;
        Mark = mark;
        UsersName = usersName;
    }
}
