package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.chunkreader.google.GoogleAnalyzer;
import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeGraphBuilderImp;
import com.developersam.web.model.chunkreader.processor.summary.TextRankSummaryGeneratorImp;
import com.developersam.web.model.chunkreader.processor.type.TypePredictorClass;
import com.google.appengine.api.datastore.Key;

import java.io.IOException;

/**
 * A factory method to generate different kinds of processors.
 * It is used to ensure quick switches between different approaches to a same
 * sub-problem.
 */
public class ProcessorFactory {

    private GoogleAnalyzer googleAnalyzer;
    private Key parentKey;

    /**
     * Construct the factory by a piece of text.
     * @param text a piece of text to be analyzed.
     * @throws IOException thrown when there is a problem with
     * Google Language API.
     */
    public ProcessorFactory(String text) throws IOException {
        googleAnalyzer = new GoogleAnalyzer(text);
    }

    public void setParentKey(Key parentKey) {
        this.parentKey = parentKey;
    }

    /**
     * A factory method to create a {@code TypePredictor}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createTypePredictor() {
        TypePredictor p = new TypePredictorClass(); // TODO when constructor is available.
        p.read(googleAnalyzer.getSentiment());
        p.setParentKey(parentKey);
        return p;
    }

    /**
     * A factory method to create a {@code KnowledgeGraphBuilder}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createKnowledgeGraphBuilder() {
        KnowledgeGraphBuilder p = new KnowledgeGraphBuilderImp(); // TODO when constructor is available.
        p.read(googleAnalyzer.getEntities());
        p.setParentKey(parentKey);
        return p;
    }

    /**
     * A factory method to create a {@code SummaryGenerator}.
     * @return a processor with all necessary information initialized.
     */
    public Processor createSummaryGenerator() {
        //SummaryGenerator p = new NaiveSummaryGenerator();
        SummaryGenerator p = new TextRankSummaryGeneratorImp();
        p.read(googleAnalyzer.getEntities(),
                googleAnalyzer.getSentences());
        p.setParentKey(parentKey);
        return p;
    }

}