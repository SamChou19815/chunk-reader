package com.developersam.web.chunkreader.processor;

import com.developersam.web.chunkreader.google.GoogleAnalyzer;
import com.developersam.web.chunkreader.processor.summary.NaiveSummaryGenerator;

import java.io.IOException;

/**
 * A factory method to generate different kinds of processors.
 * It is used to ensure quick switches between different approaches to a same
 * sub-problem.
 */
public class ProcessorFactory {

    private GoogleAnalyzer googleAnalyzer;

    /**
     * Construct the factory by a piece of text.
     * @param text a piece of text to be analyzed.
     * @throws IOException thrown when there is a problem with
     * Google Language API.
     */
    public ProcessorFactory(String text) throws IOException {
        googleAnalyzer = new GoogleAnalyzer(text);
    }

    /**
     * A factory method to create a {@code TypePredictor}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createTypePredictor() {
        TypePredictor p = null; // TODO when constructor is available.
        p.read(googleAnalyzer.getSentiment());
        return p;
    }

    /**
     * A factory method to create a {@code KnowledgeGraphBuilder}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createKnowledgeGraphBuilder() {
        KnowledgeGraphBuilder p = null; // TODO when constructor is available.
        p.read(googleAnalyzer.getEntities());
        return p;
    }

    /**
     * A factory method to create a {@code SummaryGenerator}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createSummaryGenerator() {
        SummaryGenerator p = new NaiveSummaryGenerator();
        p.read(googleAnalyzer.getEntities(),
                googleAnalyzer.getSentences(),
                googleAnalyzer.getTokens());
        return p;
    }

}