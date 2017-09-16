package com.developersam.web.test;

import com.google.cloud.language.v1beta2.AnalyzeSentimentResponse;
import com.google.cloud.language.v1beta2.Document;
import com.google.cloud.language.v1beta2.Document.Type;
import com.google.cloud.language.v1beta2.LanguageServiceClient;
import com.google.cloud.language.v1beta2.Sentiment;

import java.io.IOException;

public class Test {

    public Sentiment analyzeSentimentText(String text) throws IOException {
        LanguageServiceClient languageApi = LanguageServiceClient.create();
        Document doc = Document.newBuilder()
                .setContent(text).setType(Type.PLAIN_TEXT).build();
        AnalyzeSentimentResponse response = languageApi.analyzeSentiment(doc);
        return response.getDocumentSentiment();
    }
}