package com.developersam.testing.chunkreader.google;

import com.developersam.web.chunkreader.google.GoogleAnalyzer;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class GoogleAnalyzerTest {

    private GoogleAnalyzer googleAnalyzer;

    public GoogleAnalyzerTest() {
        try {
            googleAnalyzer = new GoogleAnalyzer("This is a test.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSentiment() throws Exception {
        assertTrue(googleAnalyzer.getSentiment() != null);
    }

    @Test
    public void getEntities() throws Exception {
    }

    @Test
    public void getSentences() throws Exception {
    }

    @Test
    public void getTokens() throws Exception {
    }


}
