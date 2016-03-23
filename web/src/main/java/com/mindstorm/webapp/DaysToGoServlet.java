package com.mindstorm.webapp;

import com.mindstorm.apputils.BusinessRunner;
import com.studio.service.doc.ResponseCode;
import com.studio.service.file.FileUtils;
import com.studio.service.templates.BaseTemplate;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

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

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(req);


            //Create a progress listener
            ProgressListener progressListener = new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int pItems) {
                    System.out.println("We are currently reading item " + pItems);
                    if (pContentLength == -1) {
                        System.out.println("So far, " + pBytesRead + " bytes have been read.");
                    } else {
                        System.out.println("So far, " + pBytesRead + " of " + pContentLength
                                + " bytes have been read.");
                    }
                }
            };
            upload.setProgressListener(progressListener);


            // Process the uploaded items
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    processFormField(item);
                } else {
                    processUploadedFile(item);
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }


    private void processUploadedFile(FileItem item) {

        String fieldName = item.getFieldName();
        String fileName = item.getName();
        String contentType = item.getContentType();
        boolean isInMemory = item.isInMemory();
        long sizeInBytes = item.getSize();


        boolean writeToFile = false;
        // Process a file upload
        if (writeToFile) {
            File uploadedFile = new File("");
            try {
                item.write(uploadedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            InputStream uploadedStream = null;
            try {
                uploadedStream = item.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            ...
            try {
                if (uploadedStream != null) {
                    uploadedStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processFormField(FileItem item) {
        String name = item.getFieldName();
        String value = item.getString();

    }
}
