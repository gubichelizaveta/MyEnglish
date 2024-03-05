package com.example.englishapp.domain;

import java.util.ArrayList;

public class ModuleDomain extends ArrayList<ModuleDomain> {
    private String Title;

    public ModuleDomain() {
    }

    public ModuleDomain(String moduleTitle) {
        Title = moduleTitle;
    }

    public String getModuleTitle() {
        return Title;
    }

    public void setModuleTitle(String moduleTitle) {
        Title = moduleTitle;
    }
}
