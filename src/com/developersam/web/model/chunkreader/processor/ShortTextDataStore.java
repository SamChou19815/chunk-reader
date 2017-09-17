package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;
import com.developersam.web.model.chunkreader.query.KnowledgeQuery;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.Date;
import java.util.List;

public class ShortTextDataStore extends DataStoreObject {

    private String keyString;
    private String text;
    private String date;
    private List<KnowledgeNodeDataStore> keywords;

    public ShortTextDataStore(Entity textEntity) {
        keyString = KeyFactory.keyToString(textEntity.getKey());
        text = textToString(textEntity.getProperty("rawText"));
        if (text.length() > 300) {
            text = text.substring(0, 300) + " ...";
        }
        date = dateFormatter((Date) textEntity.getProperty("date"));
        keywords = new KnowledgeQuery(textEntity.getKey()).getTop3KeyWords();
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

    public String getDate() {
        return date;
    }

    public List<KnowledgeNodeDataStore> getKeywords() {
        return keywords;
    }
}