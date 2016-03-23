package com.mindstorm.webapp;

import com.google.gson.Gson;
import com.studio.service.action.QueryDocDao;
import com.studio.service.action.UserDao;
import com.studio.service.action.impl.QueryDocDaoImpl;
import com.studio.service.action.impl.UserDaoImpl;
import com.studio.service.doc.ResponseCode;
import com.studio.service.templates.BaseTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        UserDao dao = new UserDaoImpl();
        resp.getWriter().println("userList");
        resp.getWriter().println(new BaseTemplate<>(dao.isAuth(token), "msg", "null", ResponseCode.OK.getCode()).toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
