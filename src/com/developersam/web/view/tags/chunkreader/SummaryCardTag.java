package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;

import java.util.List;

public class SummaryCardTag extends CardTag {

    private List<AnnotatedSentence> annotatedSentenceList;

    public void setTextDataStore(TextDataStore textDataStore) {
        this.annotatedSentenceList = textDataStore.getAnnotatedSentenceList();
        setTitle("Summary");
        setBodyContent("Summary: " + annotatedSentenceList);
    }
}