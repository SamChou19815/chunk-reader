package com.developersam.web.chunkreader.processor.summary;


import com.google.cloud.language.v1beta2.Entity;

import java.util.ArrayList;
import java.util.List;

public class AnnotatedSentenceSalienceBuilder {


    private List<AnnotatedSentence> asList;
    private double[][] similarityMatrix;
    private int count;
    private  double[] temp_salience;


    /**
     * Construct ANnotatedSentenceSalienceBuilder
     * Initialize sentenceSalience list with the length of AnnotatedSentences
     * @param as
     */
    public AnnotatedSentenceSalienceBuilder(List<AnnotatedSentence> as, double[][] similarityMatrix) {
        asList = as;
        this.similarityMatrix = similarityMatrix;
        count = 0;
        temp_salience = new double[as.size()];
        recordSalience();
    }

    private void recordSalience(){
        for(int i = 0 ; i < asList.size(); i ++){
            temp_salience[i] = asList.get(i).getSalience();
        }

    }

    private boolean smallChange(double[] temp){
        for(int i = 0 ; i < asList.size(); i ++){
            if(Math.abs(temp[i] - asList.get(i).getSalience())/temp[i] > 0.01){
                return false;
            }
        }
        return true;
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

        if(count == 100){
            count = 0;
            if(smallChange(temp_salience)) return;
            recordSalience();
        }
        else count ++;

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

        int random_next_point = (int)(Math.random()*asList.size());
        calculateSentenceSalience(Math.floorMod((random_next_point), asList.size()));


    }

}

