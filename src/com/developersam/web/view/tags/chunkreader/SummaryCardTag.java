package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class SummaryCardTag extends CardTag {

    private List<AnnotatedSentence> annotatedSentenceList;

    public void setTextDataStore(TextDataStore textDataStore) {
        this.annotatedSentenceList = textDataStore.getAnnotatedSentenceList();
        setTitle("Summary");
    }

    private void printSummary() throws JspException, IOException {
        CardTextTag contentTag = new CardTextTag();
        contentTag.setParent(this);
        contentTag.setBodyContent("Summary: " + annotatedSentenceList);
        contentTag.doTag();
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printSummary();
    }
}