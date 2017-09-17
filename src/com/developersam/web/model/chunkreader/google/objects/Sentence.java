package com.developersam.web.model.chunkreader.google.objects;

import com.google.gson.JsonObject;

public class Sentence {

    public TextSpan textSpan;
    public Sentiment sentiment;

    public Sentence(JsonObject sentence) {
        textSpan = new TextSpan(sentence.get("text").getAsJsonObject());
        sentiment = new Sentiment(sentence.get("sentiment").getAsJsonObject());
    }

    @Override
    public String toString() {
        return "{textSpan: " + textSpan + ",sentiment:" + sentiment + "}";
    }
}