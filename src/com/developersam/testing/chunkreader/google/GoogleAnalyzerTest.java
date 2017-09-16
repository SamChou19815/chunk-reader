package com.developersam.testing.chunkreader.google;

import com.developersam.web.chunkreader.google.GoogleAnalyzer;
import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.cloud.language.v1beta2.Sentiment;
import com.google.cloud.language.v1beta2.Token;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class GoogleAnalyzerTest {

    private GoogleAnalyzer googleAnalyzer;

    public GoogleAnalyzerTest() {
        try {
            googleAnalyzer = new GoogleAnalyzer("Google, headquartered " +
                    "in Mountain View, unveiled the new Android phone at the " +
                    "Consumer Electronic Show.  Sundar Pichai said in his " +
                    "keynote that users love their new Android phones.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSentiment() throws Exception {
        Sentiment sentiment = googleAnalyzer.getSentiment();
        System.out.println(sentiment);
        assertTrue(sentiment != null);
    }

    @Test
    public void getEntities() throws Exception {
        List<Entity> entityList = googleAnalyzer.getEntities();
        System.out.println(entityList);
        System.out.println(entityList.get(0).getSentiment());
        System.out.println("===========");
        assertTrue(entityList != null);
    }

    @Test
    public void getSentences() throws Exception {
        List<Sentence> sentenceList = googleAnalyzer.getSentences();
        System.out.println(sentenceList);
        assertTrue(sentenceList != null);
    }

    @Test
    public void getTokens() throws Exception {
        List<Token> tokenList = googleAnalyzer.getTokens();
        System.out.println(tokenList);
        assertTrue(tokenList != null);
    }


}
