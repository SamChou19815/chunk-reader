package com.developersam.web.chunkreader.processor;

public class ProcessorFactory {

    private String text;

    /**
     * Construct the factory by a piece of text.
     * @param text a piece of text to be analyzed.
     */
    public ProcessorFactory(String text) {
        this.text = text;
    }

    /**
     * A factory method to create a {@code TypePredictor}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createTypePredictor() {
        return null;
    }

    /**
     * A factory method to create a {@code KnowledgeGraphBuilder}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createKnowledgeGraphBuilder() {
        return null;
    }

    /**
     * A factory method to create a {@code SummaryGenerator}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createSummaryGenerator() {
        return null;
    }

}