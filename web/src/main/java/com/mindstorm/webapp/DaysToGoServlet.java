package com.mindstorm.webapp;

import com.mindstorm.apputils.BusinessRunner;
import com.studio.service.doc.ResponseCode;
import com.studio.service.templates.BaseTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DaysToGoServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        String[] params = req.getParameterValues("uid");
        if (params == null) {
            writer.println(new BaseTemplate<>(false, "miss param uid", null, ResponseCode.UNKNOWN.getCode()));
            return;
        }
        int uid = Integer.valueOf(params[0]);
        if (uid == 0) {
            writer.println(new BaseTemplate<>(false, "param uid is invalid", null, ResponseCode.UNKNOWN.getCode()));
        } else {
            resp.setContentType("text/plain");
            resp.getWriter().println(new BusinessRunner().queryUserInfo(uid));
        }
        System.out.println("begin");
        File f = new File("D:\\Idea workspace\\GradleWebAppSample-master\\testUpload.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("createFile error");
            System.out.println("cur directory :" + new File("").getCanonicalPath());
        }
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = req.getInputStream();
            fos = new FileOutputStream(f);
            int len;
            byte[] buffer = new byte[512];
            while (true) {
                System.out.println("read");
                len = is.read(buffer, 0, buffer.length);
                if (len == -1) {
                    System.out.println("len == -1 and break;");
                    break;
                }

                System.out.println("write");
                fos.write(buffer, 0, len);
            }
            is.close();
            fos.close();
            System.out.println("success");
            resp.getWriter().println("upload success");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
