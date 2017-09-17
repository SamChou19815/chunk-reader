package com.developersam.web.model.chunkreader.query;

import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;
import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import java.util.List;

public class TextQuery {

    private TextDataStore textDataStore;

    public TextQuery(Entity textEntity) {
        Key parentKey = textEntity.getKey();
        List<KnowledgeNodeDataStore> knowledgeNodeDataStoreList =
                new KnowledgeQuery(parentKey).getListOfKnowledgeNodes();
        List<AnnotatedSentence> annotatedSentenceList =
                new SummaryQuery(parentKey).getAnnotatedSentences();
        textDataStore = new TextDataStore(textEntity,
                knowledgeNodeDataStoreList,
                annotatedSentenceList);
    }

    public TextDataStore getTextObject() {
        return textDataStore;
    }
}