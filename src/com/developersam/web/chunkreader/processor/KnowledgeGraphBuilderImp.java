package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Entity;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeGraphBuilderImp implements KnowledgeGraphBuilder {


    private ArrayList<ArrayList<KnowledgeNode>> graph;
    private List<KnowledgeNode> nodes;
    private final int TYPESIZE = 6;


    public KnowledgeGraphBuilderImp(){
        graph = new ArrayList<ArrayList<KnowledgeNode>>();
        for(int i = 0;i < TYPESIZE; i++){
            ArrayList<KnowledgeNode> l = new ArrayList<>();
            graph.add(l);
        }

    }


    public void read(List<Entity> entityList){
       for(int i = 0; i < entityList.size();i++){
            KnowledgeNode n = new KnowledgeNode(entityList.get(i));
            nodes.add(n);
        }

    }

    /**
     * Distribute all nodes to their corresponding category.
     * The nodes under same category is arranged in decreasing salience.
     */
    protected void nodes_process(){
        for(int i = 0; i < nodes.size();i++){
            KnowledgeNode n = nodes.get(i);
            switch(n.getType()){
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

        for(int i = 0; i < graph.size();i++) {
            graph.get(i).sort((n1, n2) -> (Double.compare(n1.getSalience(), n2.getSalience())));
        }
    }


    public void process(){
        nodes_process();

    }
}

