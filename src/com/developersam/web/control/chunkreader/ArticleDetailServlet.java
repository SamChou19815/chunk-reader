package com.developersam.web.control.chunkreader;

import com.developersam.web.model.chunkreader.query.MainQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArticleDetailServlet", value = "/articleDetail")
public class ArticleDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyString = request.getParameter("key");
        String limitStr = request.getParameter("limit");
        int limit = 5;
        if (limitStr != null) {
            try {
                limit = Integer.parseInt(limitStr);
            } catch (NumberFormatException e) {
                // do nothing.
            }
        }
        MainQuery query = new MainQuery();
        request.setAttribute("textData", query.getTextData(keyString, limit));
        request.getRequestDispatcher("/articleDetail.jsp")
                .forward(request, response);
    }
}