package com.mindstorm.webapp;

import com.studio.service.io.IOUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DoUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            out.println("格式错误：is not multipart");
            return;
        }


        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request
        try {

            String realPath = request.getRealPath(request.getServerName());
            realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
            String fileFolder = realPath + "\\upload\\";
            File directory = new File(fileFolder);
            if (!directory.exists()) {
                directory.mkdir();
            }
            out.println("realPath :" + realPath);
            out.println("directory :" + directory);
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    out.println("processFormFiled :" + item.getName());
                    processFormField(item, directory.getAbsolutePath());
                } else {
                    out.println("processUploadedFile :" + item.getName());
                    processUploadedFile(item);
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    private void processUploadedFile(FileItem item) {
        String name = item.getFieldName();
        String value = item.getString();
    }

    private void processFormField(FileItem item, String absolutePath) throws IOException {
        String fieldName = item.getFieldName();
        String fileName = item.getName();
        String contentType = item.getContentType();
        boolean isInMemory = item.isInMemory();
        long sizeInBytes = item.getSize();
        File f = new File(absolutePath + "\\" + fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        try {
            InputStream uploadedStream = item.getInputStream();
            IOUtils.readInpustream(uploadedStream, new FileOutputStream(f), new IOUtils.OnProgress() {
                @Override
                public void onStart() {

                }

                @Override
                public void onProgress(int progress) {

                }

                @Override
                public void onStop(int code) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
