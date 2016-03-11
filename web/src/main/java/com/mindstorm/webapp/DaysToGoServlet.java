package com.mindstorm.webapp;

import com.mindstorm.apputils.BusinessRunner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DaysToGoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String[] params = req.getParameterValues("uid");
        int uid = Integer.valueOf(params[0]);
        resp.setContentType("text/plain");
        resp.getWriter().println(new BusinessRunner().queryUserInfo(uid));
    }
}
