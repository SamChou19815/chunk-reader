package com.developersam.web.chunkreader.google;

import com.google.cloud.language.v1beta2.*;
import com.google.cloud.language.v1beta2.Document.Type;

import java.io.IOException;
import java.util.List;

public class GoogleAnalyzer{
    private LanguageServiceClient languageApi;
    private String text;
    private List<Token> token;
    private List<Entity> entity;
    private List<Sentence> sentence;
    private Sentiment sentiment;

    public GoogleAnalyzer(String text1) {
        try {
            languageApi = LanguageServiceClient.create();
        } catch (IOException e) {
            // no nothing
        }
        text = text1;
    }

    public Sentiment analyzeSentimentText(String text) throws IOException {
        Document doc = Document.newBuilder()
                .setContent(text).setType(Type.PLAIN_TEXT).build();
        AnalyzeSentimentResponse response = languageApi.analyzeSentiment(doc);
        return response.getDocumentSentiment();
    }

    public List<Entity> analyzeEntitiesText(String text) throws IOException {
        Document doc = Document.newBuilder()
                .setContent(text).setType(Type.PLAIN_TEXT).build();
        AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16).build();
        AnalyzeEntitiesResponse response = languageApi.analyzeEntities(request);
        return response.getEntitiesList();
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

    public List<Sentence> analyzeSentences(String text) throws IOException {
        Document doc = Document.newBuilder()
                .setContent(text).setType(Type.PLAIN_TEXT).build();
        AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16).build();
        AnalyzeSyntaxResponse response = languageApi.analyzeSyntax(request);
        List<Sentence> sentences = response.getSentencesList();
        return response.getSentencesList();
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public List<Token> getToken() {
        return token;
    }

    public List<Entity> getEntity() {
        return entity;
    }

    public List<Sentence> getSentence(){
        return sentence;
    }

    public void setSentiment(String text) throws IOException {
        this.sentiment = analyzeSentimentText(text);
    }

    public void setToken (String text) throws IOException {
        this.token = analyzeSyntaxText(text);
    }

    public void setEntity (String text) throws IOException {
        this.entity = analyzeEntitiesText(text);
    }

    public void setSentences(String text) throws IOException {
        this.sentence = analyzeSentences(text);
    }

}