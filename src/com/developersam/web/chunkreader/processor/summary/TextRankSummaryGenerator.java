package com.developersam.web.chunkreader.processor.summary;

import com.google.cloud.language.v1beta2.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * A TextRank Summary Generator will try to evaluate the importance of each
 * sentence based on the TextRank algorithm. This class does not have a concrete
 * similarity measure.
 * @author Sam.
 */
public abstract class TextRankSummaryGenerator
        extends AbstractSummaryGenerator {

    protected List<AnnotatedSentence> annotatedSentenceList;
    protected double[][] similarityMatrix;

    /**
     * Calculate the similarity between two sentences.
     * Subclasses should implement this if they have different similarity
     * measure.
     * @param s1 sentence s1.
     * @param s2 sentence s2.
     * @return the similarity between 2 sentences. 
     */
    protected abstract double calculateSimilarity(Sentence s1, Sentence s2);

    private void buildListOfAnnotatedSentences() {
        annotatedSentenceList = new ArrayList<>();
        for (Sentence sentence: sentenceList) {
            annotatedSentenceList.add(new AnnotatedSentence(parentKey,
                    sentence, Math.random() * 1000));
        }
    }

    private void buildSimilarityMatrix() {
        final int sentenceListSize = sentenceList.size();
        similarityMatrix = new double[sentenceListSize][sentenceListSize];
        for (int i = 0; i < sentenceList.size(); i++) {
            Sentence s1 = sentenceList.get(i);
            for (int j = 0; j < sentenceList.size(); j++) {
                if (i == j) {
                    similarityMatrix[i][j] = 0;
                } else {
                    Sentence s2 = sentenceList.get(j);
                    similarityMatrix[i][j] = calculateSimilarity(s1, s2);
                }
            }
        }
    }

    private void randomVisit() {
        int randomStartingPoint =
                (int) (Math.random() * annotatedSentenceList.size());
        AnnotatedSentence randomSentence =
                annotatedSentenceList.get(randomStartingPoint);
        
        // TODO
    }

    private void randomVisitUntilConverge() {
        // TODO
    }

    @Override
    protected List<AnnotatedSentence> getEvaluatedSentences() {
        // Step 1: Build a list of annotated sentences
        buildListOfAnnotatedSentences();
        // Step 2: Build similarity matrix
        buildSimilarityMatrix();
        // Step 3: Random visiting of graph
        randomVisitUntilConverge();
        // Step 4: Return!
        return annotatedSentenceList;
    }
}
