package com.developersam.web.model.chunkreader.query;

import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeQuery extends DataStoreObject {

    public KnowledgeQuery(Key parentKey) {
        super("TextKnowledgeGraph");
        setParentKey(parentKey);
    }

    public List<List<KnowledgeNodeDataStore>> getListOfKnowledgeNodes() {
        Query q = getQuery()
                .addSort("type", Query.SortDirection.ASCENDING);
        PreparedQuery pq = getPreparedQuery(q);
        List<List<KnowledgeNodeDataStore>> list =
                new ArrayList<>(5);
        // 6 number of types
        for (int i = 0; i < 6; i++) {
            list.add(new ArrayList<>());
        }
        for (Entity knowledgeNodeEntity: pq.asIterable()) {
            KnowledgeNodeDataStore knowledgeNodeDataStore =
                    new KnowledgeNodeDataStore(knowledgeNodeEntity);
            int type = knowledgeNodeDataStore.getType();
            if (type == 0 || type == 6 || type == 7) {
                continue;
            }
            if (knowledgeNodeDataStore.getUrl() == null) {
                continue;
            }
            list.get(type-1).add(knowledgeNodeDataStore);
        }
        return list;
    }

    public List<KnowledgeNodeDataStore> getTop3KeyWords() {
        List<KnowledgeNodeDataStore> keywords = new ArrayList<>(3);
        Query q = getQuery().addSort("salience",
                Query.SortDirection.DESCENDING);
        PreparedQuery pq = getPreparedQuery(q);
        for (Entity entity: pq.asList(FetchOptions.Builder.withLimit(3))) {
            keywords.add(new KnowledgeNodeDataStore(entity));
        }
        return keywords;
    }

}