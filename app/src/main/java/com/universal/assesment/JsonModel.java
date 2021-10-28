package com.universal.assesment;

public class JsonModel {

    String language,author,type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonModel( String language, String author, String type) {
        this.language = language;
        this.author = author;
        this.type = type;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
