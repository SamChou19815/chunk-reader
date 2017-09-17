package com.developersam.web.control.chunkreader;

import com.developersam.web.model.chunkreader.processor.MainProcessor;

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
        MainProcessor processor = new MainProcessor("Google, " +
                "headquartered in Mountain View, unveiled the new Android " +
                "phone at the Consumer Electronic Show.  Sundar Pichai said " +
                "in his keynote that users love their new Android phones.");
        processor.process();
    }

}