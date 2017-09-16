package com.developersam.web.chunkreader.processor.summary;


import com.google.cloud.language.v1beta2.Entity;

import java.util.ArrayList;
import java.util.List;

public class AnnotatedSentenceSalienceBuilder {

    private double[] sentenceSalience;
    private ArrayList<AnnotatedSentence> asList;
    private WeightCalculator wc;

    public AnnotatedSentenceSalienceBuilder() {

    }

    /**
     * Construct ANnotatedSentenceSalienceBuilder
     * Initialize sentenceSalience list with the length of AnnotatedSentences
     * @param as
     */
    public AnnotatedSentenceSalienceBuilder(ArrayList<AnnotatedSentence> as, List<Entity> entityList) {
        wc = new WeightCalculator(entityList, as);
        sentenceSalience = new double[as.size()];
        for (int i=0; i<as.size(); i++) {
            sentenceSalience[i] = 1;
        }
        asList = as;
    }

    public double[] getSentenceSalience() {
        return sentenceSalience;
    }


    /**
     *
     *
     * getWeight(AnnotatedSentence a, AnnotatedSentence b); -> calculate the weight between a and b
     * need to fix the above
     * @param i the ith item in the list
     */
    public void calculateSentenceSalience(int i) {
        double sumRatio = 0.0;
        double sentenceSalienceOrig = sentenceSalience[i];

        for (int j=0; j<asList.size(); j++) {
            if (j != i) {
                double weightji = wc.weight(asList.get(j), asList.get(i));
                double sumWeightjk = 0.0;

                for (int k=0; k<asList.size(); k++) {
                    if (k != j) {
                        sumWeightjk = sumWeightjk + wc.weight(asList.get(j), asList.get(k));
                    }
                }

                double ratio = weightji / sumWeightjk * sentenceSalience[j];

                sumRatio = sumRatio + ratio;
            }
        }

        sentenceSalience[i] = 0.2 + 0.8*sumRatio;

        if ((Math.abs(sentenceSalience[i]-sentenceSalienceOrig)/sentenceSalienceOrig)<0.01) {
            return;
        }
        else {
            calculateSentenceSalience(Math.floorMod((i+1), asList.size()));
        }
    }

}
