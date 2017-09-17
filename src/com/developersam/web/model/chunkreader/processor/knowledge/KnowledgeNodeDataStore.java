package com.developersam.web.model.chunkreader.processor.knowledge;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class KnowledgeNodeDataStore extends DataStoreObject {

    private String name;
    private int type;
    private String url;
    private double salience;
    private int sentimentScore;

    public KnowledgeNodeDataStore(Key parentKey, KnowledgeNode node) {
        super("TextKnowledgeGraph");
        setParentKey(parentKey);
        Entity knowledgeGraphEntity = getNewEntity();
        knowledgeGraphEntity.setProperty("name", node.getName());
        knowledgeGraphEntity.setProperty("type", node.getType());
        knowledgeGraphEntity.setProperty("URL", node.getMetadataURL());
        knowledgeGraphEntity.setProperty("salience", node.getSalience());
        knowledgeGraphEntity.setProperty("sentimentScore",
                node.getSentimentScore());
        putIntoDatabase(knowledgeGraphEntity);
    }

    public KnowledgeNodeDataStore(Entity knowledgeGraphEntity) {
        name = (String) knowledgeGraphEntity.getProperty("name");
        type = (int) (long) knowledgeGraphEntity.getProperty("type");
        url = (String) knowledgeGraphEntity.getProperty("URL");
        salience = (double) knowledgeGraphEntity.getProperty("salience");
        sentimentScore = (int) (long) knowledgeGraphEntity
                .getProperty("sentimentScore");
    }

    @Override
    public String toString() {
        return "{name:\"" + name + "\",type:" + type + ",url:\"" + url +
                "\",salience:" + salience +
                ",sentimentScore:" + sentimentScore + "}";
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public double getSalience() {
        return salience;
    }

    public int getSentimentScore() {
        return sentimentScore;
    }
}