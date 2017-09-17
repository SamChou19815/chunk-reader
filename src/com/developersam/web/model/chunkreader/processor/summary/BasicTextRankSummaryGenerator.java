package com.developersam.web.model.chunkreader.processor.summary;

import com.developersam.web.model.chunkreader.google.objects.Sentence;

import java.util.List;

public class BasicTextRankSummaryGenerator extends TextRankSummaryGenerator{

    AnnotatedSentenceSalienceBuilder salienceBuilder;
    WeightCalculator weightCalculator;

    public BasicTextRankSummaryGenerator(){
        weightCalculator = new WeightCalculator(entityList, annotatedSentenceList);
        salienceBuilder = new AnnotatedSentenceSalienceBuilder(annotatedSentenceList, similarityMatrix);
    }

    @Override
    protected double calculateSimilarity(Sentence s1, Sentence s2) {
        return weightCalculator.weight(
                new AnnotatedSentence(parentKey, s1, Math.random()*1000),
                new AnnotatedSentence(parentKey, s2, Math.random()*1000));
    }

}
