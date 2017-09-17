package com.developersam.web.control.chunkreader;

import com.google.cloud.language.v1beta2.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1beta2.Document;
import com.google.cloud.language.v1beta2.Document.Type;
import com.google.cloud.language.v1beta2.EncodingType;
import com.google.cloud.language.v1beta2.LanguageServiceClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * A sample application that uses the Natural Language API to perform
 * entity, sentiment and syntax analysis.
 */
@WebServlet(name = "Analyze", value = "/test2")
public class Analyze extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LanguageServiceClient languageApi = LanguageServiceClient.create();
        Document doc = Document.newBuilder()
                .setContent("This is a test.").setType(Type.PLAIN_TEXT).build();
        AnalyzeEntitiesRequest request1 = AnalyzeEntitiesRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16).build();
        languageApi.analyzeEntities(request1);
    }
}