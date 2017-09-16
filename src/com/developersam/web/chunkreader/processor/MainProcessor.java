package com.developersam.web.chunkreader.processor;

import com.google.appengine.api.datastore.Key;

import java.io.IOException;

/**
 * Main processor is responsible for add the text to database and call all
 * other processors.
 * @author Sam.
 */
public class MainProcessor implements Processor {

    private ProcessorFactory factory;

    public MainProcessor(String text) throws IOException {
        factory = new ProcessorFactory(text);
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

        // Step 2: Call other processors.
        factory.createTypePredictor().process();
        factory.createKnowledgeGraphBuilder().process();
        factory.createSummaryGenerator().process();
    }
}
