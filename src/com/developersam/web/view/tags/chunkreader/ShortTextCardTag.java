package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.button.LinkButtonTag;
import com.developersam.web.devsuit.tags.components.card.CardActionsTag;
import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextBorderedTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.model.chunkreader.processor.ShortTextDataStore;
import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNodeDataStore;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class ShortTextCardTag extends CardTag {

    private ShortTextDataStore shortTextDataStore;

    public void setShortText(ShortTextDataStore shortTextDataStore) {
        this.shortTextDataStore = shortTextDataStore;
        StringBuilder sb = new StringBuilder();
        sb.append("Keywords: ");
        for (KnowledgeNodeDataStore node: shortTextDataStore.getKeywords()) {
            sb.append(node.getName()).append(", ");
        }
        int len = sb.length();
        setTitle(sb.delete(len-2, len).toString());
    }

    private void printDate() throws JspException, IOException {
        CardTextTag contentTag = new CardTextBorderedTag();
        contentTag.setParent(this);
        contentTag.setBodyContent("Added: " + shortTextDataStore.getDate());
        contentTag.doTag();
    }

    private void printShortText() throws JspException, IOException {
        CardTextTag contentTag = new CardTextTag();
        contentTag.setParent(this);
        contentTag.setBodyContent(shortTextDataStore.getText());
        contentTag.doTag();
    }

    private void printActions() throws JspException, IOException {
        CardActionsTag cardActionsTag = new CardActionsTag();
        cardActionsTag.setParent(this);
        LinkButtonTag linkButtonReadMoreTag = new LinkButtonTag();
        linkButtonReadMoreTag.setHref("articleDetail?key="
                + shortTextDataStore.getKeyString());
        linkButtonReadMoreTag.setOpenInNewTab(true);
        linkButtonReadMoreTag.setBodyContent("Read More");
        cardActionsTag.addChildrenTag(linkButtonReadMoreTag);
        cardActionsTag.doTag();
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printDate();
        printShortText();
        printActions();
    }
}