package com.studio.service.download;

/**
 * Created by Administrator on 2016/3/21.
 */
public interface IDownloader {

    //同时使用的线程数量
    void setThreadCount(int count);

    int getThreadCount();

    //设置要下载的文件网络地址；
    void setUrl(String url);

    String getUrl();

    //处理就绪任务,如果是继续下载，判断是否是同一个文件；
    void prepare();

    //开始下载
    void start();

    //暂停下载，保存下载进度
    void pause();

    //结束下载，清除下载进度的记录；
    void stop();

    //设置要保存的文件名
    void setSaveDirectoryPath(String toSavePath);

    //获取要保存的文件名
    String getSaveDirectoryPath();

    void onProgressChange(float percent);

}
