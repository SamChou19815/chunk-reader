package com.developersam.web.chunkreader.processor.knowledge;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import java.util.List;

class KnowledgeGraphDataStore extends DataStoreObject {

    private List<KnowledgeNode> nodes;

    KnowledgeGraphDataStore(Key parentKey, List<KnowledgeNode> nodes) {
        super("TextKnowledgeGraph");
        setParentKey(parentKey);
        this.nodes = nodes;
    }

    public void putIntoDatabase() {
        for (KnowledgeNode node: nodes) {
            Entity knowledgeGraphEntity = getNewEntity();
            /*
             * name
             * type
             * URL
             * salience
             * sentimentScore
             */
            knowledgeGraphEntity.setProperty("name", node.getName());
            knowledgeGraphEntity.setProperty("type", node.getType());
            knowledgeGraphEntity.setProperty("URL", node.getMetadataURL());
            knowledgeGraphEntity.setProperty("salience", node.getSalience());
            knowledgeGraphEntity.setProperty("sentimentScore",
                    node.getSentimentScore());
            putIntoDatabase(knowledgeGraphEntity);
        }
    }

}