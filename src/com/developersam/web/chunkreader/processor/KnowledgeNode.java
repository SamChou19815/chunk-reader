package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.EntityMention;

import java.util.ArrayList;
import java.util.List;

public abstract class KnowledgeNode {
    private Entity entity;
    private String name;
    private Entity.Type type;
    private String metadataURL;
    private float salience;
    //private ArrayList<EntityMention> ;


    public KnowledgeNode(Entity e){
        entity = e;
        process(e);
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
    /**
     * Obtain the name, type, metadata URL, and salience. metadataURL might be null.
     * @param e
     */
    public void process(Entity e){
        name = e.getName();
        type = e.getType();
        metadataURL = e.getMetadataMap().get("wikipedia_url");
        salience = e.getSalience();

    }

}
