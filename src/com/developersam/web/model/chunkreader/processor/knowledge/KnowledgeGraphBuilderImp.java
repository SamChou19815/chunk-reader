package com.developersam.web.model.chunkreader.processor.knowledge;

import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.processor.KnowledgeGraphBuilder;
import com.google.appengine.api.datastore.Key;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeGraphBuilderImp implements KnowledgeGraphBuilder {

    private List<KnowledgeNode> nodes;

    private Key parentKey;

    @Override
    public void setParentKey(Key parentKey) {
        this.parentKey = parentKey;
    }

    @Override
    public void read(List<Entity> entityList) {
        nodes = new ArrayList<>();
        for (Entity entity: entityList) {
            KnowledgeNode n = new KnowledgeNode(entity);
            nodes.add(n);
        }
    }

    @Override
    public void process() {
        for (KnowledgeNode node: nodes) {
            new KnowledgeNodeDataStore(parentKey, node);
        }
    }
}

