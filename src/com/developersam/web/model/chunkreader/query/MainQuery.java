package com.developersam.web.model.chunkreader.query;

import com.developersam.web.model.chunkreader.processor.ShortTextDataStore;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;

import java.util.ArrayList;
import java.util.List;

public class MainQuery extends DataStoreObject {

    public MainQuery() {
        super("Text");
    }

    public List<ShortTextDataStore> getShortTexts() {
        PreparedQuery pq = getPreparedQuery(getQuery());
        List<ShortTextDataStore> data = new ArrayList<>();
        for (Entity textEntity: pq.asIterable()) {
            data.add(new ShortTextDataStore(textEntity));
        }
        return data;
    }

    public TextDataStore getTextData(String keyString, int limit) {
        Entity textEntity = getEntityByKey(KeyFactory.stringToKey(keyString));
        return new TextQuery(textEntity, limit).getTextObject();
    }
}