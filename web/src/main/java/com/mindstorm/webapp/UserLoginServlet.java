package com.mindstorm.webapp;

import com.studio.service.action.UserDao;
import com.studio.service.action.impl.UserDaoImpl;
import com.studio.service.database.DBHelper;
import com.studio.service.doc.ResponseCode;
import com.studio.service.templates.BaseTemplate;

import javax.servlet.RequestDispatcher;
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
//        RequestDispatcher d = request.getRequestDispatcher("index.jsp");
//        d.forward(request,response);
        String uname = request.getParameter("uname");
        String upassword = request.getParameter("upassword");
        UserDao dao = new UserDaoImpl();
        String token = dao.login(uname, upassword);
        if (token == null) {
            response.getWriter().println(new BaseTemplate<>(false, "user can not find", null, ResponseCode.UNKNOWN.getCode()));
        } else {
            response.getWriter().println(new BaseTemplate<>(true, "login success", token, ResponseCode.UNKNOWN.getCode()));
        }
    }
}
