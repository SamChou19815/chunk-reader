package com.developersam.web.chunkreader.processor;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;

public class TextDataStore extends DataStoreObject {

    private String text;
    private int type;

    public TextDataStore(Entity textEntity) {
        super("Text");
        text = textToString(textEntity.getProperty("rawText"));
        type = (int) textEntity.getProperty("type");
    }

    @Override
    public String toString() {
        return "{rawText:\"" + text + "\",type:" + type + "}";
    }
}