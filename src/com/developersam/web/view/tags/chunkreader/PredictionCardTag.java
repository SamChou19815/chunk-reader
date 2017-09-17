package com.developersam.web.view.tags.chunkreader;

import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.model.chunkreader.processor.TextDataStore;

public class PredictionCardTag extends CardTag {

    public void setTextDataStore(TextDataStore textDataStore) {
        setTitle("Prediction");
        setBodyContent("Type: " + textDataStore.getType());
    }

}