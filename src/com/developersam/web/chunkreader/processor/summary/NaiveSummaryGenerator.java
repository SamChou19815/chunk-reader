package com.developersam.web.chunkreader.processor.summary;

import java.util.List;

/**
 * A naive summary generator decides the importance of sentence simply by
 * summing up the salience of each keyword in it.
 */
public class NaiveSummaryGenerator extends AbstractSummaryGenerator {

    @Override
    protected List<AnnotatedSentence> getEvaluatedSentences() {
        return null;
    }
}
