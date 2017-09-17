package com.developersam.testing.model.chunkreader.google;

import com.developersam.web.model.chunkreader.google.GoogleAnalyzer;
import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.google.objects.Sentence;
import com.developersam.web.model.chunkreader.google.objects.Sentiment;
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
        assertTrue(entityList != null);
    }

    @Test
    public void getSentences() throws Exception {
        List<Sentence> sentenceList = googleAnalyzer.getSentences();
        System.out.println(sentenceList);
        assertTrue(sentenceList != null);
    }


}
