package com.developersam.web.chunkreader.processor;

/**
 * A processor is a program that process the text in some ways and stores the
 * process result into the database.
 */
public interface Processor {

    /**
     * Process the document in some ways.
     */
    void process();

}