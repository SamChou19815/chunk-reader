package com.developersam.web.control.chunkreader;

import com.developersam.web.model.chunkreader.query.MainQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArticleServlet", value="/")
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MainQuery query = new MainQuery();
        request.setAttribute("shortTexts", query.getShortTexts());
        request.getRequestDispatcher("/article.jsp")
                .forward(request, response);
    }
}