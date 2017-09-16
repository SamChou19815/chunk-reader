package com.developersam.web.control.scheduler;

import com.developersam.web.model.scheduler.Scheduler;
import com.developersam.web.model.scheduler.SchedulerUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that loads list of scheduler items onto /apps/scheduler/.
 */
@WebServlet(name = "LoadItemsServlet", value="/apps/scheduler/")
public class LoadItemsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Scheduler scheduler = new Scheduler();
        request.setAttribute("schedulerItems", scheduler.getAllSchedulerItems());
        request.setAttribute("schedulerUser", new SchedulerUser());
        request.getRequestDispatcher("/apps/scheduler/index.jsp").forward(request, response);
    }

}