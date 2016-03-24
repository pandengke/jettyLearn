package com.studio.service.download.impl;

import com.studio.service.download.BaseDownloader;
import com.studio.service.io.FileUtils;
import com.studio.service.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SimpleDownloader extends BaseDownloader {

    private String fileName;

    private File file;

    @Override
    public void setThreadCount(int count) {
        throw new UnsupportedOperationException("SimpleDownloader can not set thread count");
    }

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
        if (getDownloadStatus() != DownloadStatus.READY) {
            throw new IllegalStateException("the download status is not ready");
        }
        setDownloadStatus(DownloadStatus.RUNNING);


        InputStream inputStream = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int totalLen = conn.getContentLength();
            inputStream = conn.getInputStream();
            fos = new FileOutputStream(file);
            IOUtils.readInpustream(inputStream, fos, new IOUtils.OnProgress() {
                @Override
                public void onStart() {

                }

                @Override
                public void onProgress(int progress) {
                    onProgressChange((float) progress / totalLen);
                }

                @Override
                public void onStop(int code) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
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
