package com.developersam.web.model.chunkreader.query;

import com.developersam.web.model.chunkreader.processor.ShortTextDataStore;
import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.users.UserServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class MainQuery extends DataStoreObject {

    public MainQuery() {
        super("Text");
    }

    public List<ShortTextDataStore> getShortTexts() {
        Filter filter = new FilterPredicate("user",
                FilterOperator.EQUAL,
                UserServiceFactory.getUserService()
                        .getCurrentUser().getNickname());
        Query query = getQuery().setFilter(filter);
        PreparedQuery pq = getPreparedQuery(query);
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