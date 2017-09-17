package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.chunkreader.google.objects.Sentiment;

/**
 * {@inheritDoc}
 * Type predictor is used to predict the type of the text and store the
 * predicted type into the database.
 */
public interface TypePredictor extends Processor {

    /**
     * Read the sentiment into your type predictor.
     * @param sentiment sentiment of the document.
     */
    void read(Sentiment sentiment);

}