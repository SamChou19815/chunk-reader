package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.EntityMention;

import java.util.ArrayList;
import java.util.List;

import static com.developersam.web.chunkreader.processor.Util.getSentimentScore;

public abstract class KnowledgeNode {
    private Entity entity;
    private String name;
    private Entity.Type type;
    private String metadataURL;
    private float salience;
    private ArrayList<int[]> entityMentions;


    public KnowledgeNode(Entity e){
        entity = e;
        name = entity.getName();
        type = entity.getType();
        metadataURL = entity.getMetadataMap().get("wikipedia_url");
        int num = entity.getMentionsCount();
        salience = entity.getSalience();
        for (int i=0; i<num; i++){
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

    public Entity.Type getType(){
        return type;
    }

    public String getMetadataURL(){
        return metadataURL;
    }

    public float getSalience(){
        return salience;
    }

    public ArrayList<int[]> getEntityMentionsData (){
        return entityMentions;
    }
    /**
     * Obtain the name, type, metadata URL, and salience. metadataURL might be null.
     * @param e
     */
    public void process(Entity e){

    }

}
