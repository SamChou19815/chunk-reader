package com.developersam.web.control.chunkreader;

import com.developersam.web.model.chunkreader.google.GoogleAnalyzer;
import com.developersam.web.model.chunkreader.google.objects.Sentiment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestServlet", value="/chunkreader/test")
public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.print("hello world");
        /*
        MainProcessor processor = new MainProcessor("Google, " +
                "headquartered in Mountain View, unveiled the new Android " +
                "phone at the Consumer Electronic Show.  Sundar Pichai said " +
                "in his keynote that users love their new Android phones.");
        processor.process();
        */
        GoogleAnalyzer googleAnalyzer = new GoogleAnalyzer("Google, headquartered " +
                "in Mountain View, unveiled the new Android phone at the " +
                "Consumer Electronic Show.  Sundar Pichai said in his " +
                "keynote that users love their new Android phones.");
        Sentiment sentiment = googleAnalyzer.getSentiment();
        response.getWriter().print(sentiment);
    }

}