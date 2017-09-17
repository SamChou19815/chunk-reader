package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import java.io.IOException;

/**
 * Main processor is responsible for add the text to database and call all
 * other processors.
 * @author Sam.
 */
public class MainProcessor extends DataStoreObject implements Processor {

    private ProcessorFactory factory;
    private String text;

    public MainProcessor(String text) throws IOException {
        super("Text");
        factory = new ProcessorFactory(text);
        this.text = text;
    }

    /**
     * This method is empty. Parent key is meaningless for parent itself.
     * @param parentKey parent key.
     */
    @Override
    public void setParentKey(Key parentKey) {}

    @Override
    public void process() {
        // Step 1: Add Text into DB.
        Entity textEntity = getNewEntity();
        textEntity.setProperty("rawText", new Text(text));
        putIntoDatabase(textEntity);
        Key parentKey = textEntity.getKey();
        factory.setParentKey(parentKey);
        // Step 2: Call other processors.
        factory.createTypePredictor().process();
        factory.createKnowledgeGraphBuilder().process();
        factory.createSummaryGenerator().process();
    }
}
