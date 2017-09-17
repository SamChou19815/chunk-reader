package com.developersam.web.model.chunkreader.query;

import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.List;

public class SummaryQuery extends DataStoreObject {

    public SummaryQuery(Key parentKey) {
        super("TextSummary");
        setParentKey(parentKey);
    }

    public List<AnnotatedSentence> getAnnotatedSentences(int limit) {
        Query q = getQuery().addSort("salience",
                Query.SortDirection.DESCENDING);
        PreparedQuery pq = getPreparedQuery(q);
        List<AnnotatedSentence> list = new ArrayList<>();
        List<Entity> result = pq.asList(FetchOptions.Builder.withLimit(limit));
        for (Entity annotatedSentenceEntity: result) {
            list.add(new AnnotatedSentence(annotatedSentenceEntity));
        }
        list.sort((s1, s2) ->
                (Integer.compare(s1.getPosition(), s2.getPosition())));
        return list;
    }

}