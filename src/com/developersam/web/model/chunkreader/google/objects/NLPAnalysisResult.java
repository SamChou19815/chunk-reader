package com.developersam.web.model.chunkreader.google.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class NLPAnalysisResult {

    public final Sentiment documentSentiment;
    public final List<Entity> entityList;
    public final List<Sentence> sentenceList;

    public NLPAnalysisResult(JsonObject root) {
        documentSentiment = new Sentiment(
                root.get("documentSentiment").getAsJsonObject());
        JsonArray entities = root.get("entities").getAsJsonArray();
        entityList = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            JsonObject entityObj = entities.get(i).getAsJsonObject();
            entityList.add(new Entity(entityObj));
        }
        JsonArray sentences = root.get("sentences").getAsJsonArray();
        sentenceList = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            JsonObject sentence = sentences.get(i).getAsJsonObject();
            sentenceList.add(new Sentence(sentence));
        }
        /*
        JsonArray tokens = root.get("tokens").getAsJsonArray();
        for (int i = 0; i < tokens.size(); i++) {
            JsonObject token = tokens.get(i).getAsJsonObject();
            JsonObject text = token.get("text").getAsJsonObject();

        }
        */
    }

    @Override
    public String toString() {
        return "{sentiment:" + documentSentiment + ",entities:" + entityList
                + ",sentences:" + sentenceList + "}";
    }
}