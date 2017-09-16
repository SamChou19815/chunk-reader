package com.developersam.web.chunkreader.processor.knowledge;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class KnowledgeNodeDataStore extends DataStoreObject {

    private String name;
    private int type;
    private String url;
    private double salience;
    private double sentimentScore;

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
        type = (int) knowledgeGraphEntity.getProperty("type");
        url = (String) knowledgeGraphEntity.getProperty("url");
        salience = (double) knowledgeGraphEntity.getProperty("salience");
        sentimentScore = (double) knowledgeGraphEntity
                .getProperty("sentimentScore");
    }

    @Override
    public String toString() {
        return "{name:\"" + name + "\",type:" + type + ",url:\"" + url +
                "\",salience:" + salience +
                ",sentimentScore:" + sentimentScore + "}";
    }
}