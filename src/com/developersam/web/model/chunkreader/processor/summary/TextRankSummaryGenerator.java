package com.developersam.web.model.chunkreader.processor.summary;

import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.google.objects.Sentence;
import com.developersam.web.model.chunkreader.google.objects.TextSpan;

import java.util.ArrayList;
import java.util.HashSet;
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
    protected ArrayList<HashSet<String>> records;

    @Override
    public void read(List<Entity> entityList, List<Sentence> sentenceList) {
        super.read(entityList, sentenceList);
        records = new ArrayList<>(sentenceList.size());
        for (int i = 0; i < sentenceList.size(); i++) {
            records.add(new HashSet<>());
        }
        for (Entity entity: entityList) {
            for (TextSpan span: entity.mentions) {
                String word = span.content;
                int sentenceID = findSentenceID(span.beginOffset);
                records.get(sentenceID).add(word);
            }
        }
    }

    /**
     * Calculate the similarity between two sentences.
     * Subclasses should implement this if they have different similarity
     * measure.
     * @param s1ID sentence s1.
     * @param s2ID sentence s2.
     * @return the similarity between 2 sentences.
     */
    protected abstract double calculateSimilarity(int s1ID, int s2ID);

    protected int findSentenceID(int pos) {
        int start = 0, end = annotatedSentenceList.size() - 1;
        while (true) {
            if (end - start <= 1) {
                return start;
            }
            int mid = (start + end) / 2;
            int midPos = annotatedSentenceList.get(mid).getPosition();
            if (pos < midPos) {
                end = mid;
            } else if ( pos > annotatedSentenceList.get(mid + 1).getPosition()) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
    }

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
        for (int i = 0; i < sentenceList.size() - 1; i++) {
            for (int j = i+1; j < sentenceList.size(); j++) {
                if (i == j) {
                    similarityMatrix[i][j] = 0;
                } else {
                    similarityMatrix[i][j] = calculateSimilarity(i, j);
                    similarityMatrix[j][i] = similarityMatrix[i][j];
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
            if (counter % num == 0) {
                if (convergent(previousResult)) {
                    // check every num times
                    return;
                } else {
                    // record for future use
                    for (int i = 0; i < num; i++) {
                        previousResult[i] =
                                annotatedSentenceList.get(i).getSalience();
                    }
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
