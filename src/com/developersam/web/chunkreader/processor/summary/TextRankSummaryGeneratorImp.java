package com.developersam.web.chunkreader.processor.summary;

import com.google.cloud.language.v1beta2.Sentence;

public class TextRankSummaryGeneratorImp extends TextRankSummaryGenerator{


    WeightCalculator weightCalculator;
    public TextRankSummaryGeneratorImp(){
        weightCalculator = new WeightCalculator(entityList, annotatedSentenceList);

    }

    @Override
    protected double calculateSimilarity(Sentence s1, Sentence s2) {
        return weightCalculator.weight(new AnnotatedSentence(parentKey,
                s1, Math.random() * 1000),new AnnotatedSentence(parentKey,
                s2, Math.random() * 1000));

    }


}
