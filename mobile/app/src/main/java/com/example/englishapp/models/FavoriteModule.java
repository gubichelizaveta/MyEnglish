package com.example.englishapp.models;

public class FavoriteModule {
    public String FavoriteModulesTitle;
    public String UsersName;

    public FavoriteModule(String moduleTitle, String userName) {
        FavoriteModulesTitle = moduleTitle;
        UsersName = userName;
    }

    public String getModuleTitle() {
        return FavoriteModulesTitle;
    }

    public String getUserName() {
        return UsersName;
    }

    public void setModuleTitle(String moduleTitle) {
        FavoriteModulesTitle = moduleTitle;
    }

    public void setUserName(String userName) {
        UsersName = userName;
    }
}
