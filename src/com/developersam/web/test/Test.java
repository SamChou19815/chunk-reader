package com.developersam.web.test;

import com.google.cloud.language.v1beta2.*;
import com.google.cloud.language.v1beta2.Document.Type;

import java.io.IOException;
import java.util.List;

public class Test {

    private LanguageServiceClient languageApi;

    public Test() {
        try {
            languageApi = LanguageServiceClient.create();
        } catch (IOException e) {
            // no nothing
        }
    }

    public Sentiment analyzeSentimentText(String text) throws IOException {
        Document doc = Document.newBuilder()
                .setContent(text).setType(Type.PLAIN_TEXT).build();
        AnalyzeSentimentResponse response = languageApi.analyzeSentiment(doc);
        return response.getDocumentSentiment();
    }

    public List<Token> analyzeSyntaxText(String text) throws IOException {
        Document doc = Document.newBuilder()
                .setContent(text).setType(Type.PLAIN_TEXT).build();
        AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16).build();
        AnalyzeSyntaxResponse response = languageApi.analyzeSyntax(request);
        List<Sentence> sentences = response.getSentencesList();
        return response.getTokensList();
    }
}