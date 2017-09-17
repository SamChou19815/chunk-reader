package com.developersam.web.model.chunkreader.processor.type;

import com.developersam.web.model.chunkreader.google.objects.Sentiment;
import com.developersam.web.model.chunkreader.processor.TypePredictor;
import com.developersam.web.model.chunkreader.processor.Util;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

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
        Entity textEntity = getEntityByKey(parentKey);
        textEntity.setProperty("type", sentimentScore);
        putIntoDatabase(textEntity);
    }

}
