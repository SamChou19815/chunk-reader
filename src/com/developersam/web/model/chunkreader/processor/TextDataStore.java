package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;
import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.List;

public class TextDataStore extends DataStoreObject {

    private String keyString;
    private String text;
    private int type;
    private List<List<KnowledgeNodeDataStore>> knowledgeNodeDataStoreList;
    private List<AnnotatedSentence> annotatedSentenceList;

    private int limit;

    public TextDataStore(Entity textEntity,
                         List<List<KnowledgeNodeDataStore>>
                                 knowledgeNodeDataStoreLst,
                         List<AnnotatedSentence> annotatedSentenceLst,
                         int limit) {
        super("Text");
        keyString = KeyFactory.keyToString(textEntity.getKey());
        text = textToString(textEntity.getProperty("rawText"));
        type = (int) (long) textEntity.getProperty("type");
        knowledgeNodeDataStoreList = knowledgeNodeDataStoreLst;
        annotatedSentenceList = annotatedSentenceLst;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "{rawText:\"" + text + "\",type:" + type +
                ",knowledgeNodeDataStoreList:" + knowledgeNodeDataStoreList +
                ",annotatedSentenceList:" + annotatedSentenceList + "}";
    }

    public String getKeyString() {
        return keyString;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }

    public List<List<KnowledgeNodeDataStore>> getKnowledgeNodeDataStoreList() {
        return knowledgeNodeDataStoreList;
    }

    public List<AnnotatedSentence> getAnnotatedSentenceList() {
        return annotatedSentenceList;
    }

    public int getLimit() {
        return limit;
    }
}