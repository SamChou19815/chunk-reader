package com.developersam.web.model.chunkreader.processor.summary;

import com.developersam.web.model.chunkreader.google.objects.Sentence;

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
            for (int j = i; j < sentenceList.size(); j++) {
                if (i == j) {
                    similarityMatrix[i][j] = 0;
                } else {
                    Sentence s2 = sentenceList.get(j);
                    similarityMatrix[i][j] = calculateSimilarity(s1, s2);
                    similarityMatrix[j][i] = calculateSimilarity(s1, s2);
                }
            }
        }
    }

    private boolean convergent(double[] previousResult) {
        double threshold = 1e-4;
        for (int i = 0; i < previousResult.length; i++) {
            if (Math.abs(previousResult[i] -
                    annotatedSentenceList.get(i).getSalience()) > threshold) {
                return false;
            }
        }
        return true;
    }

    private int randomSentenceID() {
        return (int)(Math.random()*annotatedSentenceList.size());
    }

    private void randomVisit() {
        int num = annotatedSentenceList.size();
        final double D = 0.85;
        AnnotatedSentence start = annotatedSentenceList.get(randomSentenceID());
        int counter = 0;
        double[] previousResult = new double[num];
        // Initialization
        for (int i = 0; i < num; i++) {
            previousResult[i] = Short.MIN_VALUE;
        }
        while (true) {
            if (counter % num == 0 && convergent(previousResult)) {
                return;
            } else {
                for (int i = 0; i < num; i++) {
                    previousResult[i] =
                            annotatedSentenceList.get(i).getSalience();
                }
            }
            int i;
            AnnotatedSentence next;
            while (true) {
                i = randomSentenceID();
                next = annotatedSentenceList.get(i);
                if (!start.equals(next)) {
                    break;
                }
            }
            double sum = 0;
            for (int j = 0; j < num; j++) {
                AnnotatedSentence inS = annotatedSentenceList.get(j);
                if (i == j) {
                    continue;
                }
                double numerator = similarityMatrix[j][i];
                double denominator = 0.0;
                for (int k = 0; k < num; k++) {
                    if (k == j) {
                        continue;
                    }
                    denominator += similarityMatrix[j][k];
                }
                sum += numerator / denominator * inS.getSalience();
            }
            double newSalience = (1-D) + D * sum;
            start.setSalience(newSalience);
            counter++;
            start = next;
        }
    }

    @Override
    protected List<AnnotatedSentence> getEvaluatedSentences() {
        buildListOfAnnotatedSentences();
        buildSimilarityMatrix();
        randomVisit();
        return annotatedSentenceList;
    }
}
