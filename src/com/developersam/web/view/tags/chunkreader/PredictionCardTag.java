package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class PredictionCardTag extends CardTag {

    private TextDataStore textDataStore;

    public void setTextDataStore(TextDataStore textDataStore) {
        this.textDataStore = textDataStore;
        setTitle("Prediction");
    }

    private void printType() throws JspException, IOException {
        CardTextTag contentTag = new CardTextTag();
        contentTag.setParent(this);
        contentTag.setBodyContent("Type: " + textDataStore.getType());
        contentTag.doTag();
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printType();
    }
}