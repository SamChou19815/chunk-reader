package com.developersam.web.model.chunkreader.google.objects;

import com.google.gson.JsonObject;

public class Sentiment {

    public final double magnitude;
    public final double score;

    public Sentiment(JsonObject sentimentObject) {
        magnitude = sentimentObject.get("magnitude").getAsDouble();
        score = sentimentObject.get("score").getAsDouble();
    }

    @Override
    public String toString() {
        return "{magnitude:" + magnitude + ",score:" + score + "}";
    }
}