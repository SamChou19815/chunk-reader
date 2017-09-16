package com.developersam.web.chunkreader.processor.type;

import com.developersam.web.chunkreader.processor.TypePredictor;
import com.developersam.web.chunkreader.processor.Util;
import com.google.cloud.language.v1beta2.Sentiment;

public class TypePredictorClass implements TypePredictor{

    int sentimentScore;
    public TypePredictorClass () {

    }

    public void read(Sentiment sentiment) {
        sentimentScore = Util.getSentimentScore(sentiment);
    }

    public void process() {

    }

}
