package com.mindstorm.webapp;

import com.mindstorm.apputils.BusinessRunner;
import com.studio.service.doc.ResponseCode;
import com.studio.service.templates.BaseTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DaysToGoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        PrintWriter writer = resp.getWriter();
        String[] params = req.getParameterValues("uid");
        if (params == null) {
            writer.println(new BaseTemplate<>(false, "miss param uid", null, ResponseCode.UNKNOWN.getCode()));
            return;
        }
        int uid = Integer.valueOf(params[0]);
        if (uid == 0) {
            writer.println(new BaseTemplate<>(false, "param uid is invalid", null, ResponseCode.UNKNOWN.getCode()));
            return;
        }
        resp.setContentType("text/plain");
        resp.getWriter().println(new BusinessRunner().queryUserInfo(uid));
    }

}
