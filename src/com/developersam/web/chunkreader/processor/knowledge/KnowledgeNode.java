package com.developersam.web.chunkreader.processor.knowledge;

import com.developersam.web.chunkreader.processor.Util;
import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.EntityMention;

import java.util.ArrayList;
import java.util.List;

import static com.developersam.web.chunkreader.processor.Util.getSentimentScore;

public class KnowledgeNode {

    private Entity entity;
    private String name;
    private int type;
    private String metadataURL;
    private float salience;
    private int sentimentScore;
    private ArrayList<int[]> entityMentions;


    public KnowledgeNode(Entity e){
        entity = e;
        name = entity.getName();
        type = entity.getType().getNumber();
        /*
         * Type - Number relation:
         * https://googleapis.github.io/googleapis/java/
         * proto-google-cloud-language-v1/0.1.18/apidocs/com/google/cloud/
         * language/v1/Entity.Type.html
         */
        // potentially problematic, because wikipedia may not be the only map
        metadataURL = entity.getMetadataMap().get("wikipedia_url");
        salience = entity.getSalience();
        sentimentScore = Util.getSentimentScore(entity.getSentiment());
        entityMentions = new ArrayList<>();

        int num = entity.getMentionsCount();
        for (int i = 0; i < num; i++){
            int [] data = {entity.getMentions(i).getText().getBeginOffset(),
                    Util.getSentimentScore(entity.getMentions(i).getSentiment())};
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

    public double getSentimentScore(){
        return sentimentScore;
    }

    public List<int[]> getEntityMentionsData(){
        return entityMentions;
    }



}
