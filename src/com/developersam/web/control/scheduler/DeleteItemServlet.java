package com.developersam.web.control.scheduler;

import com.developersam.web.model.scheduler.Scheduler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that delete an scheduler item.
 */
@WebServlet(name = "DeleteItemServlet", value="/apps/scheduler/delete")
public class DeleteItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Scheduler scheduler = new Scheduler();
        scheduler.delete(request.getParameter("key"));
    }

}