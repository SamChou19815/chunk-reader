package com.developersam.web.control.scheduler;

import com.developersam.web.model.scheduler.SchedulerUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that delete an scheduler item.
 */
@WebServlet(name = "ChangeEmailNotificationSettingServlet",
        value="/apps/scheduler/changeEmailNotificationSetting")
public class ChangeEmailNotificationSettingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enabledStr = request.getParameter("emailNotificationEnabled");
        boolean enabled = enabledStr.equals("true");
        SchedulerUser schedulerUser = new SchedulerUser();
        schedulerUser.setEmailNotificationEnabled(enabled);
    }

}