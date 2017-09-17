package com.developersam.web.model.chunkreader.processor;


import com.developersam.web.model.chunkreader.google.objects.Entity;

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