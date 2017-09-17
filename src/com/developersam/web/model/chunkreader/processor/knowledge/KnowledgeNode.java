package com.developersam.web.model.chunkreader.processor.knowledge;

import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.processor.Util;

import java.util.ArrayList;
import java.util.List;


public class KnowledgeNode {

    private Entity entity;
    private String name;
    private int type;
    private String metadataURL;
    private double salience;
    private int sentimentScore;
    private ArrayList<int[]> entityMentions;

    public KnowledgeNode(Entity e){
        entity = e;
        name = entity.name;
        type = entity.type;
        metadataURL = entity.url;
        salience = entity.salience;
        sentimentScore = Util.getSentimentScore(entity.sentiment);
        entityMentions = new ArrayList<>();
        int num = entity.mentions.length;
        for (int i = 0; i < num; i++){
            int [] data = {entity.mentions[i].beginOffset,
                    Util.getSentimentScore(entity.sentiment)};
            entityMentions.add(data);
        }
    }


    public Entity getEntity() {
        return entity;
    }

    public String getName(){
        return name;
    }

    public int getType(){
        return type;
    }

    public String getMetadataURL(){
        return metadataURL;
    }

    public double getSalience(){
        return salience;
    }

    public int getSentimentScore(){
        return sentimentScore;
    }

    public List<int[]> getEntityMentionsData(){
        return entityMentions;
    }



}
