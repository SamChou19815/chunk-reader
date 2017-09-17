package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.button.LinkButtonTag;
import com.developersam.web.devsuit.tags.components.card.CardActionsTag;
import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class SummaryCardTag extends CardTag {

    private TextDataStore textDataStore;
    private List<AnnotatedSentence> annotatedSentenceList;

    public void setTextDataStore(TextDataStore textDataStore) {
        this.textDataStore = textDataStore;
        this.annotatedSentenceList = textDataStore.getAnnotatedSentenceList();
        setTitle("Summary");
    }

    private void printSummary() throws JspException, IOException {
        CardTextTag contentTag = new CardTextTag();
        contentTag.setParent(this);
        StringBuilder sb = new StringBuilder();
        for (AnnotatedSentence sentence: annotatedSentenceList) {
            sb.append(sentence.getSentence()).append(' ');
        }
        contentTag.setBodyContent(sb.toString());
        contentTag.doTag();
    }

    private void printActions() throws JspException, IOException {
        CardActionsTag cardActionsTag = new CardActionsTag();
        String key = textDataStore.getKeyString();
        int currentLimit = textDataStore.getLimit();
        cardActionsTag.setParent(this);
        LinkButtonTag linkButtonMoreTag = new LinkButtonTag();
        linkButtonMoreTag.setHref("articleDetail?key=" + key
                + "&limit=" + (currentLimit+1));
        linkButtonMoreTag.setOpenInNewTab(false);
        linkButtonMoreTag.setBodyContent("More Details");
        cardActionsTag.addChildrenTag(linkButtonMoreTag);
        if (currentLimit > 1) {
            LinkButtonTag linkButtonLessTag = new LinkButtonTag();
            linkButtonLessTag.setHref("articleDetail?key=" + key
                    + "&limit=" + (currentLimit - 1));
            linkButtonLessTag.setOpenInNewTab(false);
            linkButtonLessTag.setBodyContent("Less Details");
            cardActionsTag.addChildrenTag(linkButtonLessTag);
        }
        cardActionsTag.doTag();
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printSummary();
        printActions();
    }
}