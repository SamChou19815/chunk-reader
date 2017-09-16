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
            ProcessorFactory factory = new ProcessorFactory("The Holocaust,[b] also referred to as the Shoah,[c] was a genocide in which some six million European Jews were killed by Adolf Hitler's Nazi Germany and its World War II collaborators. The victims included 1.5 million children, and constituted about two-thirds of the nine million Jews who had previously resided in Continental Europe. A broader definition of the Holocaust includes non-Jewish victims such as the Romani, ethnic Poles, members of other Slavic ethnic groups and Aktion T4 patients who were mentally and physically disabled. An even broader definition includes Soviet citizens and prisoners of war, homosexuals, Jehovah's Witnesses, black people, political opponents of the Nazis, and members of other smaller groups.");

            System.out.println(((TypePredictorClass)factory.createTypePredictor()).getSentimentScore());
            List<KnowledgeNode> nodes = ((KnowledgeGraphBuilderImp)factory.createKnowledgeGraphBuilder()).getNodes();
            for(int i =0; i < nodes.size(); i ++){
                System.out.println(nodes.get(i).getName());
//                System.out.println("Entity mentions size: "+nodes.get(i).getEntityMentionsData().size());
//                for (int j=0; j<nodes.get(i).getEntityMentionsData().size(); j++) {
//                    System.out.println("Begin offset " + nodes.get(i).getEntityMentionsData().get(j)[0]);
//                    System.out.println("SentimentScore " + nodes.get(i).getEntityMentionsData().get(j)[1]);
//
//                }
                System.out.println(nodes.get(i).getSentimentScore());
                System.out.println(nodes.get(i).getSalience());

                if(nodes.get(i).getMetadataURL() != null) System.out.println(nodes.get(i).getMetadataURL());

                System.out.println();

            }





        }catch (IOException e){

        }

    }
}
