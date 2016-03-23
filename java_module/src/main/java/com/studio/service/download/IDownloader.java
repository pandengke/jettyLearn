package com.studio.service.download;

/**
 * Created by Administrator on 2016/3/21.
 */
public interface IDownloader {

    void setThreadCount(int count);

    int getThreadCount();

    void setUrl(String url);

    String getUrl();

    void prepare();

    void start();

    void pause();

    void stop();

    void setSaveDirectoryPath(String toSavePath);

    String getSaveDirectoryPath();

    void onProgressChange(float percent);

}
