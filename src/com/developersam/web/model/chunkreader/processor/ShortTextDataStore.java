package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;

public class ShortTextDataStore extends DataStoreObject {

    private String keyString;
    private String text;

    public ShortTextDataStore(Entity textEntity) {
        keyString = KeyFactory.keyToString(textEntity.getKey());
        text = textToString(textEntity.getProperty("rawText")).
                substring(0, 300) + " ...";
    }

    @Override
    public String toString() {
        return "{key:\"" + keyString + "\",text:\"" + text + "\"}";
    }

    public String getKeyString() {
        return keyString;
    }

    public String getText() {
        return text;
    }
}