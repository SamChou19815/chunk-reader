package com.developersam.web.chunkreader.processor.summary;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import java.util.List;

class SummaryGeneratorDataStore extends DataStoreObject {

    private List<AnnotatedSentence> annotatedSentenceList;

    SummaryGeneratorDataStore(Key parentKey,
                              List<AnnotatedSentence> annotatedSentenceList) {
        super("TextSummary");
        setParentKey(parentKey);
        this.annotatedSentenceList = annotatedSentenceList;
    }

    void putIntoDatabase() {
        for (AnnotatedSentence annotatedSentence: annotatedSentenceList) {
            Entity summaryEntity = getNewEntity();
            summaryEntity.setProperty(
                    "sentence", annotatedSentence.getSentence());
            summaryEntity.setProperty(
                    "position", annotatedSentence.getPosition());
            summaryEntity.setProperty(
                    "salience", annotatedSentence.getSalience());
            putIntoDatabase(summaryEntity);
        }
    }

}