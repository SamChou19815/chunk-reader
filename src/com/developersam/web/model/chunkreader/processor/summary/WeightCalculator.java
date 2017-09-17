package com.developersam.web.model.chunkreader.processor.summary;

import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeNode;

import java.util.ArrayList;
import java.util.List;

public class WeightCalculator {
    List<Entity> entityList;
    List<AnnotatedSentence> annotatedSentenceList;

    public WeightCalculator(List<Entity> entityList, List<AnnotatedSentence> annotatedSentenceList){
        this.entityList = entityList;
        this.annotatedSentenceList = annotatedSentenceList;
    }

    public double weight(int s1ID, int s2ID){
        /*
        ArrayList<KnowledgeNode> n1Keywords = new ArrayList<>();
        ArrayList<KnowledgeNode> n2Keywords = new ArrayList<>();
        for(int i = 0; i < entityList.size();i++){
            KnowledgeNode keyword = new KnowledgeNode(entityList.get(i));
            List<int[]> list = keyword.getEntityMentionsData();
            for(int j = 0; j < list.size();j++){
                int num = 0; // findSentenceID(list.get(j)[0]);
                if(num == n1.getPosition()) n1Keywords.add(keyword);
                if(num == n2.getPosition()) n2Keywords.add(keyword);
            }
        }
        double weightSum = 0.0;
        for (int i = 0; i < n1Keywords.size();i++){
            KnowledgeNode keyword1 = n1Keywords.get(i);
            for (int j = 0; j < n2Keywords.size();j++){
                KnowledgeNode keyword2 = n2Keywords.get(j);
                weightSum += wordWeight(keyword1, keyword2);
            }
        }
        return weightSum/Math.log(n1.getSentence().length() + n2.getSentence().length());
        */
        return 0;
    }

    private double wordWeight(KnowledgeNode e1, KnowledgeNode e2){
        int e1Score = e1.getSentimentScore();
        int e2Score = e2.getSentimentScore();

        double mag1 = 1.0, mag2 = 1.0;
        if(e1Score%2 == 1)  mag1 = 0.5;
        if(e2Score%2 == 1)  mag2 = 0.5;

        int pos_or_neg = 1;
        if((e1Score == 3 || e1Score == 4) && (e2Score == 5 || e2Score == 6))
            pos_or_neg = 0;
        if((e1Score == 5 || e1Score == 6) && (e2Score == 3 || e2Score == 4))
            pos_or_neg = 0;

        return mag1 + mag2 + pos_or_neg;
    }

}
