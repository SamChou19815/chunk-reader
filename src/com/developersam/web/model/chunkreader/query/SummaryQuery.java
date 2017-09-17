package com.developersam.web.model.chunkreader.query;

import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;

import java.util.ArrayList;
import java.util.List;

public class SummaryQuery extends DataStoreObject {

    public SummaryQuery(Key parentKey) {
        super("TextSummary");
        setParentKey(parentKey);
    }

    public List<AnnotatedSentence> getAnnotatedSentences() {
        PreparedQuery pq = getPreparedQuery(getQuery());
        List<AnnotatedSentence> list = new ArrayList<>();
        List<Entity> result = pq.asList(FetchOptions.Builder.withLimit(10));
        for (Entity annotatedSentenceEntity: result) {
            list.add(new AnnotatedSentence(annotatedSentenceEntity));
        }
        return list;
    }

}