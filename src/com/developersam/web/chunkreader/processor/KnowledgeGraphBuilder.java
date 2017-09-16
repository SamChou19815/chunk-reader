package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Entity;

import java.util.List;

/**
 * {@inheritDoc}
 * Knowledge Graph Builder is used to build a knowledge graph and stores the
 * built graph into the database.
 */
public interface KnowledgeGraphBuilder extends Processor {

    /**
     * Read the list of entities into your knowledge graph builder.
     * @param entityList list of entities.
     */
    void read(List<Entity> entityList);

}