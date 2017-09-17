package com.developersam.web.model.chunkreader.processor;

import com.google.appengine.api.datastore.Key;

/**
 * A processor is a program that process the text in some ways and stores the
 * process result into the database.
 */
public interface Processor {

    /**
     * Set the parent key for all related entities.
     * All sub-processor should implement this method to get the key and store
     * them as instance variable.
     * See Google Datastore's setAncestor.
     * @param parentKey parent key.
     */
    void setParentKey(Key parentKey);

    /**
     * Process the document in some ways.
     */
    void process();

}