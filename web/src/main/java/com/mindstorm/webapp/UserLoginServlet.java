package com.mindstorm.webapp;

import com.studio.service.database.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10.
 */
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = request.getIntHeader("uid");
        response.getWriter().println("onPost");
        response.getWriter().println("onPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = 0;
        String[] params = request.getParameterValues("uid");
        uid = Integer.valueOf(params[0]);

        response.getWriter().println("onGet");
        response.getWriter().println("onGet");
    }
}
