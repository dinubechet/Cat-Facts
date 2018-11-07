package com.example.dinu.catzz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatFacts {
    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    @SerializedName("createdAt")
    private String createdAt;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
