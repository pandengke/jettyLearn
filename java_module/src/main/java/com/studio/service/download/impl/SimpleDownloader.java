package com.studio.service.download.impl;

import com.studio.service.download.BaseDownloader;
import com.studio.service.file.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SimpleDownloader extends BaseDownloader {

    private String fileName;

    private File file;

    @Override
    public void prepare() {
        setDownloadStatus(DownloadStatus.STOPPED);
        String folder = getSaveDirectoryPath();
        fileName = getUrl().substring(getUrl().lastIndexOf('/') + 1);
        try {
            file = FileUtils.createFileAutoIncrese(folder + "\\" + fileName);
            setDownloadStatus(DownloadStatus.READY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        setDownloadStatus(DownloadStatus.RUNNING);
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024 * 512];
        try {
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bis = new BufferedInputStream(conn.getInputStream());
//            InputStream is = conn.getInputStream();
            fos = new FileOutputStream(file);
            long totalLen = conn.getContentLength();
            int readLen;
            int sum = 0;
            while (getDownloadStatus() == DownloadStatus.RUNNING) {
                readLen = bis.read(buffer, 0, buffer.length);
                if (readLen == -1) {
                    break;
                }
                sum += readLen;
                onProgressChange((float) sum / totalLen);
                fos.write(buffer, 0, readLen);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("SimpleDownloader can not pause");
    }

    @Override
    public void stop() {
        setDownloadStatus(DownloadStatus.STOPPED);
    }

    @Override
    public void onProgressChange(float percent) {
        throw new UnsupportedOperationException("you must override this method");
    }
}
