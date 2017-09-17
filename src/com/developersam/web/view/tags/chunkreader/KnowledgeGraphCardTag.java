package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class KnowledgeGraphCardTag extends CardTag {

    private List<KnowledgeNodeDataStore> knowledgeNodeDataStoreList;

    public void setTextDataStore(TextDataStore textDataStore) {
        this.knowledgeNodeDataStoreList = textDataStore.getKnowledgeNodeDataStoreList();
        setTitle("Knowledge Graph:");
    }

    private void printGraph() throws JspException, IOException {
        CardTextTag contentTag = new CardTextTag();
        contentTag.setParent(this);
        contentTag.setBodyContent("Graph: " + knowledgeNodeDataStoreList);
        contentTag.doTag();
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printGraph();
    }
}