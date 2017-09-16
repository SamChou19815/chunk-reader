package com.developersam.web.chunkreader.processor.type;

import com.developersam.web.chunkreader.processor.TypePredictor;
import com.developersam.web.chunkreader.processor.Util;
import com.google.appengine.api.datastore.Key;
import com.google.cloud.language.v1beta2.Sentiment;

public class TypePredictorClass implements TypePredictor{

    /**
     * Sentiment score is essentially the type of article.
     */
    private int sentimentScore;

    public TypePredictorClass () {

    }

    public void read(Sentiment sentiment) {
        sentimentScore = Util.getSentimentScore(sentiment);
    }

    @Override
    public void setParentKey(Key parentKey) {

    }

    public void process() {

    }

}
