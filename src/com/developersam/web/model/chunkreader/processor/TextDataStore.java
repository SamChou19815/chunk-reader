package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;
import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;

import java.util.List;

public class TextDataStore extends DataStoreObject {

    private String text;
    private int type;
    private List<List<KnowledgeNodeDataStore>> knowledgeNodeDataStoreList;
    private List<AnnotatedSentence> annotatedSentenceList;

    public TextDataStore(Entity textEntity,
                         List<List<KnowledgeNodeDataStore>> knowledgeNodeDataStoreLst,
                         List<AnnotatedSentence> annotatedSentenceLst) {
        super("Text");
        text = textToString(textEntity.getProperty("rawText"));
        type = (int) (long) textEntity.getProperty("type");
        knowledgeNodeDataStoreList = knowledgeNodeDataStoreLst;
        annotatedSentenceList = annotatedSentenceLst;
    }

    @Override
    public String toString() {
        return "{rawText:\"" + text + "\",type:" + type +
                ",knowledgeNodeDataStoreList:" + knowledgeNodeDataStoreList +
                ",annotatedSentenceList:" + annotatedSentenceList + "}";
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
}