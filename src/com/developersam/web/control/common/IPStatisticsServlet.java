package com.developersam.web.control.common;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * A helpful servlet super class that helps to retrieve the real ip address to help ip statistics
 */
public class IPStatisticsServlet extends HttpServlet {

    /**
     * This method helps to extract real ip from proxy.
     * It must be used in ip statistics to obtain meaningful ip.
     * @param request request
     * @return real ip address
     */
    protected static String getIPAddress(HttpServletRequest request) {
        String remoteAddress = "";
        if (request != null) {
            remoteAddress = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddress == null || "".equals(remoteAddress)) {
                remoteAddress = request.getRemoteAddr();
            }
        }
        return remoteAddress;
    }

}