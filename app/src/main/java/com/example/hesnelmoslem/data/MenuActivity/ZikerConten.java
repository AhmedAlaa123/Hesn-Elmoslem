package com.example.hesnelmoslem.data.MenuActivity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ZikerConten implements Serializable {

    private int id;

    private String title;

    private String audio_url;

    private String textUrl;

    public ZikerConten(int id, String title, String audio_url, String textUrl) {
        this.id = id;
        this.title = title;
        this.audio_url = audio_url;
        this.textUrl = textUrl;
    }

    public ZikerConten() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl;
    }
}
