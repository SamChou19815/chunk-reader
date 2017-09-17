package com.developersam.web.model.chunkreader.google.objects;

import com.google.gson.JsonObject;

public class TextSpan {

    public final String content;
    public final int beginOffset;

    public TextSpan(JsonObject object) {
        content = object.get("content").getAsString();
        beginOffset = object.get("beginOffset").getAsInt();
    }

    @Override
    public String toString() {
        return "{content:\"" + content + "\"beginOffset:" + beginOffset + "}";
    }
}