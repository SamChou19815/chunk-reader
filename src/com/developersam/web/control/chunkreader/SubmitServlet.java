package com.developersam.web.control.chunkreader;

import com.developersam.web.model.chunkreader.processor.MainProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitServlet", value = "/submit")
public class SubmitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String text = request.getParameter("text");
            MainProcessor processor = new MainProcessor(text);
            processor.process();
            response.getWriter().print(true);
        } catch (IOException e) {
            response.getWriter().print(false);
        }
    }
}