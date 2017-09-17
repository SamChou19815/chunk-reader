package com.developersam.web.model.chunkreader.processor.summary;

public class AdvancedTextRankSummaryGenerator extends TextRankSummaryGenerator{

    AnnotatedSentenceSalienceBuilder salienceBuilder;
    WeightCalculator weightCalculator;

    public AdvancedTextRankSummaryGenerator(){
        weightCalculator = new WeightCalculator(entityList, annotatedSentenceList);
        salienceBuilder = new AnnotatedSentenceSalienceBuilder(annotatedSentenceList, similarityMatrix);
    }


    @Override
    protected double calculateSimilarity(int s1ID, int s2ID) {
        return weightCalculator.weight(s1ID, s2ID);
    }

}
