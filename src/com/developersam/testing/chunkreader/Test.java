package com.developersam.testing.chunkreader;

import com.developersam.web.chunkreader.google.GoogleAnalyzer;
import com.developersam.web.chunkreader.processor.ProcessorFactory;
import com.developersam.web.chunkreader.processor.knowledge.KnowledgeGraphBuilderImp;
import com.developersam.web.chunkreader.processor.knowledge.KnowledgeNode;
import com.developersam.web.chunkreader.processor.type.TypePredictorClass;
import com.google.cloud.language.v1beta2.Sentiment;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args){

        try{
            ProcessorFactory factory = new ProcessorFactory("The word2vec tool takes a text corpus as" +
                    " input and produces the word vectors as output. It first constructs a vocabulary from the training " +
                    "text data and then learns vector representation of words. The resulting word vector file can be used " +
                    "as features in many natural language processing and machine learning applications. " +
                    "A simple way to investigate the learned representations is to find the closest words for a " +
                    "user-specified word. The distance tool serves that purpose. For example, if you enter 'france', distance " +
                    "will display the most similar words and their distances to 'france', which should look like:");

            System.out.println(((TypePredictorClass)factory.createTypePredictor()).getSentimentScore());
            List<KnowledgeNode> nodes = ((KnowledgeGraphBuilderImp)factory.createKnowledgeGraphBuilder()).getNodes();
            for(int i =0; i < nodes.size(); i ++){
                System.out.println(nodes.get(i).getName());
                System.out.println(nodes.get(i).getSentimentScore());
                System.out.println(nodes.get(i).getSalience());
                
                if(nodes.get(i).getMetadataURL() != null) System.out.println(nodes.get(i).getMetadataURL());
            }





        }catch (IOException e){

        }

    }
}
