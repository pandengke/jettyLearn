package com.studio.service.download;

/**
 * Created by Administrator on 2016/3/21.
 */
public abstract class BaseDownloader implements IDownloader {

    public enum DownloadStatus {
        READY, RUNNING, STOPPED
    }

    private String url;
    private String fileDirectory;

    private int threadCount;

    private DownloadStatus downloadStatus;

    @Override
    public void setThreadCount(int count) {
        this.threadCount = count;
    }

    @Override
    public int getThreadCount() {
        return threadCount;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setSaveDirectoryPath(String toSavePath) {
        this.fileDirectory = toSavePath;
    }

    @Override
    public String getSaveDirectoryPath() {
        return fileDirectory;
    }

    public DownloadStatus getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
}
