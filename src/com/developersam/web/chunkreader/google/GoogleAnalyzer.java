package com.developersam.web.chunkreader.google;

import com.google.cloud.language.v1beta2.*;
import com.google.cloud.language.v1beta2.Document.Type;

import java.io.IOException;
import java.util.List;

/**
 * Google Analyzer is used to simply extract the calculated results from Google
 * API.
 */
public class GoogleAnalyzer{

    /**
     * Common Google Language API.
     */
    private LanguageServiceClient languageAPI;
    /**
     * Document built from the given text.
     */
    private Document doc;
    /**
     * Response used to analyze syntax.
     */
    private AnalyzeSyntaxResponse response;
    /**
     * Sentiment of the entire document.
     */
    private Sentiment sentiment;
    /**
     * List of entities extracted from the text.
     */
    private List<Entity> entities;
    /**
     * List of sentences extracted from the text.
     */
    private List<Sentence> sentences;
    /**
     * List of tokens extracted from the text.
     */
    private List<Token> tokens;

    /**
     * Construct a {@code GoogleAnalyzer} from the text needed to analyze.
     * @param text text to be analyzed.
     * @throws IOException thrown when there is a problem reading the text.
     */
    public GoogleAnalyzer(String text) throws IOException {
        languageAPI = LanguageServiceClient.create();
        doc = Document.newBuilder()
                .setContent(text).setType(Type.PLAIN_TEXT).build();
        AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16).build();
        response = languageAPI.analyzeSyntax(request);
        process();
    }

    /**
     * Process the text.
     * @throws IOException thrown when there is a problem processing the text.
     */
    private void process() throws IOException {
        findSentiment();
        findEntities();
        findSentences();
        findTokens();
    }

    /**
     * Find sentiment of the document
     * @throws IOException thrown when there is a problem finding sentiment.
     */
    private void findSentiment() throws IOException {
        AnalyzeSentimentResponse response = languageAPI.analyzeSentiment(doc);
        sentiment = response.getDocumentSentiment();
    }

    /**
     * Find entities in the document.
     * @throws IOException thrown when there is a problem finding entities.
     */
    private void findEntities() throws IOException {
        AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16).build();
        AnalyzeEntitiesResponse response = languageAPI.analyzeEntities(request);
        entities = response.getEntitiesList();
    }

    /**
     * Find sentences in the document.
     * @throws IOException thrown when there is a problem finding sentences.
     */
    private void findSentences() throws IOException {
        sentences = response.getSentencesList();
    }

    /**
     * Find tokens in the document.
     * @throws IOException thrown when there is a problem finding tokens.
     */
    private void findTokens() throws IOException {
        tokens = response.getTokensList();
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Sentence> getSentences(){
        return sentences;
    }

    public List<Token> getTokens() {
        return tokens;
    }

}