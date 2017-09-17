package com.developersam.web.model.chunkreader.google;

import com.developersam.web.model.chunkreader.google.objects.NLPAnalysisResult;
import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

class APIFetcher {

    private static final String TARGET_URL =
            "https://language.googleapis.com/v1beta2/documents:annotateText?" +
                    "fields=documentSentiment" +
                    "%2Centities%2Clanguage%2Csentences%2Ctokens&";
    private static final String API_KEY =
            "key=AIzaSyCCmkB_ImxrIQdgl_ej84QgW0rQsh0_Huk";
    private String text;

    APIFetcher(String text) {
        this.text = text;
    }

    private String getResponse() throws IOException {
        URL serverUrl = new URL(TARGET_URL + API_KEY);
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setDoOutput(true);
        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));
        httpRequestBodyWriter.write
                ("{" +
                        " \"document\": {" +
                        "  \"content\": \"" + text + "\"," +
                        "  \"type\": \"PLAIN_TEXT\"" +
                        " },\n" +
                        " \"encodingType\": \"UTF8\"," +
                        " \"features\": {" +
                        "  \"extractDocumentSentiment\": true," +
                        "  \"extractEntities\": true," +
                        "  \"extractEntitySentiment\": true," +
                        "  \"extractSyntax\": true" +
                        " }" +
                        "}");
        httpRequestBodyWriter.close();
        StringBuilder sb = new StringBuilder();
        InputStreamReader in = new InputStreamReader(
                httpConnection.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String text = "";
        while ((text = br.readLine()) != null) {
            sb.append(text);
        }
        return sb.toString();
    }

    NLPAnalysisResult getResult() throws IOException {
        JsonObject root =
                new JsonParser().parse(getResponse()).getAsJsonObject();
        return new NLPAnalysisResult(root);
    }

}