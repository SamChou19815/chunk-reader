package com.developersam.web.control.scheduler;

import com.developersam.web.model.scheduler.Scheduler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that add a new item to the database.
 */
@WebServlet(name = "AddItemServlet", value="/apps/scheduler/add")
public class AddItemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Scheduler scheduler = new Scheduler();
        String description = request.getParameter("description");
        String deadline = request.getParameter("deadline");
        boolean success = scheduler.addItem(description, deadline);
        response.getWriter().print(success);
        response.setCharacterEncoding("UTF-8");
    }

}