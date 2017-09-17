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
        String type;
        switch (textDataStore.getType()) {
            case 1:
                type = "The author is probably describing a debate on an issue.";
                break;
            case 2:
                type = "The author is probably illustrating a concept.";
                break;
            case 3:
                type = "The author probably opposes an opinion slightly.";
                break;
            case 4:
                type = "The author probably opposes an opinion strongly.";
                break;
            case 5:
                type = "The author probably supports an opinion slightly.";
                break;
            case 6:
                type = "The author probably supports an opinion strongly.";
                break;
            default:
                type = "We don't know what's going on.";
        }
        contentTag.setBodyContent(type);
        /*
         * 1 ==> neutral weak (debate type)
         * 2 ==> neutral strong (illustrative)
         * 3 ==> negative weak (slightly oppose)
         * 4 ==> negative strong (strongly oppose)
         * 5 ==> positive weak (slightly support)
         * 6 ==> positive strong (strongly support)
         */
        contentTag.doTag();
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printType();
    }
}