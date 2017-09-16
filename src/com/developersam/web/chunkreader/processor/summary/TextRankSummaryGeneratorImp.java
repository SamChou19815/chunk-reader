package com.developersam.web.chunkreader.processor.summary;

import com.google.cloud.language.v1beta2.Sentence;

import java.util.List;

public class TextRankSummaryGeneratorImp extends TextRankSummaryGenerator{

    AnnotatedSentenceSalienceBuilder salienceBuilder;
    WeightCalculator weightCalculator;

    public TextRankSummaryGeneratorImp(){
        weightCalculator = new WeightCalculator(entityList, annotatedSentenceList);
        AnnotatedSentenceSalienceBuilder salienceBuilder = new AnnotatedSentenceSalienceBuilder(annotatedSentenceList, similarityMatrix);
    }

    @Override
    protected double calculateSimilarity(Sentence s1, Sentence s2) {
        return weightCalculator.weight(new AnnotatedSentence(parentKey,
                s1, Math.random() * 1000),new AnnotatedSentence(parentKey,
                s2, Math.random() * 1000));

    }

    protected void setSalience(){
        int randomStartingPoint = (int) (Math.random() * annotatedSentenceList.size());
        salienceBuilder.calculateSentenceSalience(randomStartingPoint);
    }

    @Override
    public void process() {
        buildListOfAnnotatedSentences();
        buildSimilarityMatrix();
        setSalience();
        for (AnnotatedSentence annotatedSentence: getEvaluatedSentences()) {
            annotatedSentence.putIntoDatabase();
        }
    }

    public List<AnnotatedSentence> getResultSentences(){
        return annotatedSentenceList;
    };


}
