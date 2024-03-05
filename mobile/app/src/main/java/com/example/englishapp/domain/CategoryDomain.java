package com.example.englishapp.domain;

public class CategoryDomain {
    private String Title;
    private String url;

    public CategoryDomain(String title, String url) {
        Title = title;
        this.url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
