package com.developersam.web.chunkreader.processor.summary;


import com.google.cloud.language.v1beta2.Entity;

import java.util.ArrayList;
import java.util.List;

public class AnnotatedSentenceSalienceBuilder {


    private List<AnnotatedSentence> asList;
    private double[][] similarityMatrix;


    /**
     * Construct AnnotatedSentenceSalienceBuilder.
     * Initialize sentenceSalience list with the length of AnnotatedSentences
     * @param as annotated sentence list.
     */
    public AnnotatedSentenceSalienceBuilder(List<AnnotatedSentence> as,
                                            double[][] similarityMatrix) {
        asList = as;
        this.similarityMatrix = similarityMatrix;
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
        double sentenceSalienceOrig = asList.get(i).getSalience();

        for (int j=0; j<asList.size(); j++) {
            if (j != i) {
                double weightji = similarityMatrix[j][i];
                double sumWeightjk = 0.0;

                for (int k=0; k<asList.size(); k++) {
                    if (k != j) {
                        sumWeightjk = sumWeightjk + similarityMatrix[j][k];
                    }
                }

                double ratio = weightji / sumWeightjk * asList.get(j).getSalience();

                sumRatio = sumRatio + ratio;
            }
        }

        asList.get(i).setSalience(0.2 + 0.8*sumRatio);

        if ((Math.abs(asList.get(i).getSalience()-sentenceSalienceOrig)/sentenceSalienceOrig)<0.01) {
            return;
        }
        else {
            calculateSentenceSalience(Math.floorMod((i+1), asList.size()));
        }
    }

}
