package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Entity;

import java.util.List;

public interface KnowledgeGraphBuilder extends Processor {

    void read(List<Entity> entityList);

}