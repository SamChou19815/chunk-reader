package com.developersam.web.chunkreader.query;

import com.developersam.web.chunkreader.processor.knowledge.KnowledgeNodeDataStore;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeQuery extends DataStoreObject {

    public KnowledgeQuery(Key parentKey) {
        super("TextKnowledge");
        setParentKey(parentKey);
    }

    public List<KnowledgeNodeDataStore> getListOfKnowledgeNodes() {
        PreparedQuery pq = getPreparedQuery(getQuery());
        List<KnowledgeNodeDataStore> list = new ArrayList<>();
        for (Entity knowledgeNodeEntity: pq.asIterable()) {
            list.add(new KnowledgeNodeDataStore(knowledgeNodeEntity));
        }
        return list;
    }

}