package com.developersam.web.control.chunkreader;

import com.developersam.web.model.chunkreader.query.MainQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticleDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyString = request.getParameter("key");
        MainQuery query = new MainQuery();
        request.setAttribute("textData", query.getTextData(keyString));
        request.getRequestDispatcher("/articleDetail.jsp")
                .forward(request, response);
    }
}