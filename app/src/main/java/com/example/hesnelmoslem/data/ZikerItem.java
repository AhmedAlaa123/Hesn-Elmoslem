package com.example.hesnelmoslem.data;

import com.google.gson.annotations.SerializedName;

public class ZikerItem {
@SerializedName("ID")
    private  int id;
    @SerializedName("ARABIC_TEXT")

    private String arabic_text;
    @SerializedName("LANGUAGE_ARABIC_TRANSLATED_TEXT")

    private String languageArabicTranslatedText;
    @SerializedName("TRANSLATED_TEXT")

    private String translatedText;
    @SerializedName("REPEAT")
    private int repeat;
    @SerializedName("AUDIO")
    private String audio;


    public ZikerItem(int id, String arabic_text, String languageArabicTranslatedText, String translatedText, int repeat, String audio) {
        this.id = id;
        this.arabic_text = arabic_text;
        this.languageArabicTranslatedText = languageArabicTranslatedText;
        this.translatedText = translatedText;
        this.repeat = repeat;
        this.audio = audio;
    }

    public ZikerItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArabic_text() {
        return arabic_text;
    }

    public void setArabic_text(String arabic_text) {
        this.arabic_text = arabic_text;
    }

    public String getLanguageArabicTranslatedText() {
        return languageArabicTranslatedText;
    }

    public void setLanguageArabicTranslatedText(String languageArabicTranslatedText) {
        this.languageArabicTranslatedText = languageArabicTranslatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
