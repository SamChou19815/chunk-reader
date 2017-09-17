package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;

import java.util.List;

public class KnowledgeGraphCardTag extends CardTag {

    private List<KnowledgeNodeDataStore> knowledgeNodeDataStoreList;

    public void setTextDataStore(TextDataStore textDataStore) {
        this.knowledgeNodeDataStoreList = textDataStore.getKnowledgeNodeDataStoreList();
        setTitle("Knowledge Graph:");
        setBodyContent("Graph: " + knowledgeNodeDataStoreList);
    }

}