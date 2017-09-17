package com.developersam.web.model.chunkreader.query;

import com.developersam.web.model.chunkreader.processor.TextDataStore;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;

import java.util.ArrayList;
import java.util.List;

public class MainQuery extends DataStoreObject {

    private List<TextDataStore> data;

    public MainQuery() {
        super("Text");
        PreparedQuery pq = getPreparedQuery(getQuery());
        data = new ArrayList<>();
        for (Entity textEntity: pq.asIterable()) {
            TextQuery textQuery = new TextQuery(textEntity);
            data.add(textQuery.getTextObject());
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public List<TextDataStore> getData() {
        return data;
    }
}