package com.studio.service.upload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/22.
 */
public class Uploader {
    private String urlPath;
    private String localPath;

    private void upload() {
        File f = new File(localPath);
        if (!f.exists()) {
            return;
        }
        URL url = null;
        BufferedOutputStream bos = null;
        FileInputStream fis = null;
        try {
            url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.addRequestProperty("FileName", localPath.substring(localPath.lastIndexOf("\\") + 1));
            conn.setRequestProperty("content-type", "text/html");
//


            bos = new BufferedOutputStream(conn.getOutputStream());
            fis = new FileInputStream(f);
            byte[] buffer = new byte[1024 * 64];
            int len;
            while (true) {
                len = fis.read(buffer, 0, buffer.length);
                if (len == -1) {
                    break;
                }
                bos.write(buffer, 0, len);
            }

            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            char[] cbuff = new char[1024];
            int clen = isr.read(cbuff, 0, cbuff.length);
            String str = new String(cbuff, 0, clen);
            System.out.println("server info:" + str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String args[]) {
        Uploader uploader = new Uploader();
        uploader.urlPath = "http://192.168.10.200:8080/web/daystogo?uid=123";
        uploader.localPath = "D:\\test_download/testUpload.txt";
        uploader.upload();
    }
}
