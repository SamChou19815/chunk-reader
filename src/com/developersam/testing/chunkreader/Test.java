package com.developersam.testing.chunkreader;

import com.developersam.web.model.chunkreader.processor.ProcessorFactory;
import com.developersam.web.model.chunkreader.processor.summary.AnnotatedSentence;
import com.developersam.web.model.chunkreader.processor.summary.TextRankSummaryGeneratorImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args){

        try{
            ProcessorFactory factory = new ProcessorFactory("Activists and politicians on Monday decried statements by an Egyptian army official who suggested that protesters in front of the cabinet of ministers should be burned in \"Hitler's ovens.\"\n" +
                    "\n" +
                    "The private newspaper Al-Shorouk reported on Monday that General Abdel Moneim Kato, an adviser to the military's Morale Affairs Department, suggested that instead of worrying about the country's welfare, people were concerned about \"some street bully who deserves to be thrown into Hitler's ovens,\" referring to protesters.\n" +
                    "\n" +
                    "Kato was attempting to justify the military's use of excessive force against protesters during clashes that broke out around the cabinet building on Saturday. The clashes led to the deaths of 11 people, according to the Health Ministry.");

            /*
            System.out.println(((TypePredictorClass)factory.createTypePredictor()).getSentimentScore());
            List<KnowledgeNode> nodes = ((KnowledgeGraphBuilderImp)factory.createKnowledgeGraphBuilder()).getNodes();
            for(int i =0; i < nodes.size(); i ++){
                System.out.println(nodes.get(i).getName());
                System.out.println(nodes.get(i).getSentimentScore());
                System.out.println(nodes.get(i).getSalience());
                
                if(nodes.get(i).getMetadataURL() != null) System.out.println(nodes.get(i).getMetadataURL());
            }
            */

            TextRankSummaryGeneratorImp p = (TextRankSummaryGeneratorImp) factory.createSummaryGenerator();
            List<AnnotatedSentence> sentences = p.getResultSentences();
            for(int i =0; i < sentences.size(); i ++){
                System.out.println(sentences.get(i).getSentence());
                System.out.println(sentences.get(i).getSalience());

                System.out.println("==========");
            }


        }catch (IOException e){

        }

    }
}
