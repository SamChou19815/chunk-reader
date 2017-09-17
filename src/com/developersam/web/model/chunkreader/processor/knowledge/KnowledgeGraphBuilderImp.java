package com.developersam.web.model.chunkreader.processor.knowledge;

import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.processor.KnowledgeGraphBuilder;
import com.google.appengine.api.datastore.Key;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeGraphBuilderImp implements KnowledgeGraphBuilder {

    // private ArrayList<ArrayList<KnowledgeNode>> graph;
    private List<KnowledgeNode> nodes;
    // private final static int TYPE_SIZE = 6;

    private Key parentKey;

    public KnowledgeGraphBuilderImp() {
        /*
        graph = new ArrayList<>();
        for (int i = 0; i < TYPE_SIZE; i++) {
            ArrayList<KnowledgeNode> l = new ArrayList<>();
            graph.add(l);
        }
        */
    }

    public List<KnowledgeNode> getNodes() {
        return nodes;
    }

    @Override
    public void setParentKey(Key parentKey) {
        this.parentKey = parentKey;
    }

    @Override
    public void read(List<Entity> entityList) {
        nodes = new ArrayList<>();
        for (int i = 0; i < entityList.size(); i++) {
            KnowledgeNode n = new KnowledgeNode(entityList.get(i));
            nodes.add(n);
        }
    }

    /**
     * Distribute all nodes to their corresponding category.
     * The nodes under same category is arranged in increasing salience.
     * (no longer applies)
     */
    /*
    protected void nodesProcess() {
        // Add a node to different parts of the graph according to their types.
        for (KnowledgeNode n: nodes) {
            switch (n.getType()) {
                case PERSON:
                    graph.get(0).add(n);
                    break;
                case LOCATION:
                    graph.get(1).add(n);
                    break;
                case ORGANIZATION:
                    graph.get(2).add(n);
                    break;
                case EVENT:
                    graph.get(3).add(n);
                    break;
                case WORK_OF_ART:
                    graph.get(4).add(n);
                    break;
                case CONSUMER_GOOD:
                    graph.get(5).add(n);
                    break;
                default:
            }
        }




        // Sort each type of collection of nodes according to their salience.
        for (int i = 0; i < graph.size(); i++) {
            graph.get(i).sort((n1, n2) -> (Double.compare(n1.getSalience(),
            n2.getSalience())));
        }

    }

    */

    @Override
    public void process() {
        for (KnowledgeNode node: nodes) {
            new KnowledgeNodeDataStore(parentKey, node);
        }
    }
}

