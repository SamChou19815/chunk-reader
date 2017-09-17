package com.developersam.web.model.chunkreader.google.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.omg.CORBA.UNKNOWN;

import java.util.Arrays;

public class Entity {

    public final String name;
    public final int type;
    public final String url;
    public final double salience;
    public final TextSpan[] mentions;
    public final Sentiment sentiment;

    public Entity(JsonObject entity) {
        name = entity.get("name").getAsString();
        String typeString = entity.get("type").getAsString();
        switch (typeString) {
            case "UNKNOWN":
                type = 0;
                break;
            case "PERSON":
                type = 1;
                break;
            case "LOCATION":
                type = 2;
                break;
            case "ORGANIZATION":
                type = 3;
                break;
            case "EVENT":
                type = 4;
                break;
            case "WORK_OF_ART":
                type = 5;
                break;
            case "CONSUMER_GOOD":
                type = 6;
                break;
            default:
                type = 7;
        }
        JsonElement wikipediaURL =
                entity.get("metadata").getAsJsonObject().get("wikipedia_url");
        url = wikipediaURL == null ? null : wikipediaURL.getAsString();
        salience = entity.get("salience").getAsDouble();
        JsonArray mentionsObj = entity.get("mentions").getAsJsonArray();
        mentions = new TextSpan[mentionsObj.size()];
        for (int i = 0; i < mentionsObj.size(); i++) {
            JsonObject textObj = mentionsObj.get(i).getAsJsonObject()
                    .get("text").getAsJsonObject();
            mentions[i] = new TextSpan(textObj);
        }
        sentiment = new Sentiment(entity.get("sentiment").getAsJsonObject());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{').append("name:\"").append(name).append("\",")
                .append("type:").append(type).append(',')
                .append("url:\"").append(url).append("\",")
                .append("mentions:").append(Arrays.toString(mentions)).
                append(",sentiment:").append(sentiment).append("}");
        return sb.toString();
    }
}