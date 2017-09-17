package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextBorderedTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class KnowledgeGraphCardTag extends CardTag {

    private TextDataStore textDataStore;
    private List<List<KnowledgeNodeDataStore>> knowledgeNodeDataStoreList;

    public void setTextDataStore(TextDataStore textDataStore) {
        this.textDataStore = textDataStore;
        this.knowledgeNodeDataStoreList =
                textDataStore.getKnowledgeNodeDataStoreList();
        setTitle("Knowledge Graph");
    }

    private void knowledgeGraphString(StringBuilder sb,
                                        List<KnowledgeNodeDataStore> nodes) {
        for (KnowledgeNodeDataStore node: nodes) {
            sb.append("<a href='").append(node.getUrl())
                    .append("' target=_blank style='margin-right:8px'>")
                    .append(node.getName()).append("</a> ");
        }
    }

    private void printKeywords() throws JspException, IOException {
        CardTextTag contentTag = new CardTextBorderedTag();
        contentTag.setParent(this);
        StringBuilder sb = new StringBuilder();
        sb.append("Keywords: ");
        for (KnowledgeNodeDataStore node: textDataStore.getKeywords()) {
            sb.append(node.getName()).append(", ");
        }
        int len = sb.length();
        setTitle(sb.delete(len-2, len).toString());
        contentTag.setBodyContent(sb.toString());
        contentTag.doTag();
    }

    private void printGraph() throws JspException, IOException {
        StringBuilder sb;
        if (!knowledgeNodeDataStoreList.get(0).isEmpty()) {
            CardTextTag contentTag1 = new CardTextTag();
            contentTag1.setParent(this);
            sb = new StringBuilder();
            sb.append("<div>People to learn:</div>");
            knowledgeGraphString(sb, knowledgeNodeDataStoreList.get(0));
            contentTag1.setBodyContent(sb.toString());
            contentTag1.doTag();
        }
        if (!knowledgeNodeDataStoreList.get(1).isEmpty()) {
            CardTextTag contentTag2 = new CardTextTag();
            contentTag2.setParent(this);
            sb = new StringBuilder();
            sb.append("<div>Locations to learn:</div>");
            knowledgeGraphString(sb, knowledgeNodeDataStoreList.get(1));
            contentTag2.setBodyContent(sb.toString());
            contentTag2.doTag();
        }
        if (!knowledgeNodeDataStoreList.get(2).isEmpty()) {
            CardTextTag contentTag3 = new CardTextTag();
            contentTag3.setParent(this);
            sb = new StringBuilder();
            sb.append("<div>Organizations to learn:</div>");
            knowledgeGraphString(sb, knowledgeNodeDataStoreList.get(2));
            contentTag3.setBodyContent(sb.toString());
            contentTag3.doTag();
        }
        if (!knowledgeNodeDataStoreList.get(3).isEmpty()) {
            CardTextTag contentTag4 = new CardTextTag();
            contentTag4.setParent(this);
            sb = new StringBuilder();
            sb.append("<div>Events to learn about:</div>");
            knowledgeGraphString(sb, knowledgeNodeDataStoreList.get(3));
            contentTag4.setBodyContent(sb.toString());
            contentTag4.doTag();
        }
        if (!knowledgeNodeDataStoreList.get(4).isEmpty()) {
            CardTextTag contentTag5 = new CardTextTag();
            contentTag5.setParent(this);
            sb = new StringBuilder();
            sb.append("<div>Works of art to learn about:</div>");
            knowledgeGraphString(sb, knowledgeNodeDataStoreList.get(4));
            contentTag5.setBodyContent(sb.toString());
            contentTag5.doTag();
        }
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printKeywords();
        printGraph();
    }
}