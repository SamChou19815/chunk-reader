package com.developersam.web.chunkreader.processor.summary;

import com.developersam.web.chunkreader.google.GoogleAnalyzer;
import com.developersam.web.chunkreader.processor.knowledge.KnowledgeNode;
import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.EntityMention;

import java.util.ArrayList;
import java.util.List;

public class WeightCalculator {
    List<Entity> entityList;
    List<AnnotatedSentence> annotatedSentenceList;

    public WeightCalculator(List<Entity> entityList, List<AnnotatedSentence> annotatedSentenceList){
        this.entityList = entityList;
        this.annotatedSentenceList = annotatedSentenceList;
    }


    private int findSentenceID(int pos) {
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

    public double weight(AnnotatedSentence n1, AnnotatedSentence n2){
        ArrayList<KnowledgeNode> n1Keywords = new ArrayList<>();
        ArrayList<KnowledgeNode> n2Keywords = new ArrayList<>();
        for(int i = 0; i < entityList.size();i++){
            KnowledgeNode keyword = new KnowledgeNode(entityList.get(i));
            List<int[]> list = keyword.getEntityMentionsData();
            for(int j = 0; j < list.size();j++){
                int num = findSentenceID(list.get(j)[0]);
                if(num == n1.getPosition()) n1Keywords.add(keyword);
                if(num == n2.getPosition()) n2Keywords.add(keyword);
            }
        }

        double weightSum = 0.0;
        for (int i = 0; i < n1Keywords.size();i++){
            KnowledgeNode keyword1 = n1Keywords.get(i);
            for (int j = 0; j < n2Keywords.size();j++){
                KnowledgeNode keyword2 = n2Keywords.get(j);
                weightSum += word_weight(keyword1, keyword2);
            }
        }
        return weightSum/Math.log(n1.getSentence().length() + n2.getSentence().length());

    }

    private double word_weight(KnowledgeNode e1, KnowledgeNode e2){
        int e1Score = (int)e1.getSentimentScore();
        int e2Score = (int)e2.getSentimentScore();

        double mag1 = 1.0, mag2 = 1.0;
        if(e1Score%2 == 1)  mag1 = 0.5;
        if(e2Score%2 == 1)  mag2 = 0.5;

        int pos_or_neg = 1;
        if((e1Score == 3 || e1Score == 4) && (e2Score == 5 || e2Score == 6)) pos_or_neg = 0;
        if((e1Score == 5 || e1Score == 6) && (e2Score == 3 || e2Score == 4)) pos_or_neg = 0;

        return mag1 + mag2 + pos_or_neg;
    }

}
