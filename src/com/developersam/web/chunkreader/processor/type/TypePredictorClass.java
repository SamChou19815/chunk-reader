package com.developersam.web.chunkreader.processor.type;

import com.developersam.web.chunkreader.processor.TypePredictor;
import com.developersam.web.chunkreader.processor.Util;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.cloud.language.v1beta2.Sentiment;

public class TypePredictorClass extends DataStoreObject
        implements TypePredictor {

    /**
     * Sentiment score is essentially the type of article.
     */
    private int sentimentScore;
    private Key parentKey;

    public TypePredictorClass () {
        super("Text");
    }

    @Override
    public void read(Sentiment sentiment) {
        sentimentScore = Util.getSentimentScore(sentiment);
    }

    @Override
    public void setParentKey(Key parentKey) {
        this.parentKey = parentKey;
    }

    public int getSentimentScore(){
        return sentimentScore;
    }

    @Override
    public void process() {
        Entity textEntity = new Entity(parentKey);
        textEntity.setProperty("type", sentimentScore);
        putIntoDatabase(textEntity);
    }

}
