package com.developersam.web.chunkreader.processor.summary;

import com.google.cloud.language.v1beta2.Sentence;

import java.util.List;

/**
 * A TextRank Summary Generator will try to evaluate the importance of each
 * sentence based on the TextRank algorithm. This class does not have a concrete
 * similarity measure.
 * @author Sam.
 */
public abstract class TextRankSummaryGenerator
        extends AbstractSummaryGenerator {

    /**
     * Calculate the similarity between two sentences.
     * Subclasses should implement this if they have different similarity
     * measure.
     * @param s1 sentence s1.
     * @param s2 sentence s2.
     * @return the similarity between 2 sentences.
     */
    protected abstract double calculateSimilarity(Sentence s1, Sentence s2);

    @Override
    protected List<AnnotatedSentence> getEvaluatedSentences() {
        return null;
    }
}
