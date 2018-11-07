package com.example.dinu.catzz;

public class CatModel {

    private String factText;
    private String date;

    public CatModel(String factText, String date) {
        this.factText = factText;
        this.date = date;
    }

    public String getFactText() {
        return factText;
    }

    public void setFactText(String factText) {
        this.factText = factText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
