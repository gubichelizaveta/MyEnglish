package com.example.englishapp.domain;

public class MostViewedDomain {
    private String Title;
    private String subtitle;
    private String url;

    public MostViewedDomain(String title, String subtitle, String url) {
        Title = title;
        this.subtitle = subtitle;
        this.url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
