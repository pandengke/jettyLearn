package com.studio.service.download;

import com.studio.service.download.impl.ResumeDownloader;
import com.studio.service.download.impl.SimpleDownloader;

/**
 * Created by Administrator on 2016/3/21.
 */
public class DownloadUtils {
    public static void main(String[] args) {
        IDownloader downloader = new ResumeDownloader() {
            int lastPercent = 0;

            @Override
            public void onProgressChange(float percent) {
                int lastPercent = (int) (percent * 100);
                if (this.lastPercent != lastPercent) {
                    this.lastPercent = lastPercent;
                    System.out.println("download progress:" + lastPercent + "/100");
                }
            }
        };
        downloader.setSaveDirectoryPath("D://test_download");
        downloader.setThreadCount(1);
        downloader.setUrl("https://download.microsoft.com/download/0/2/A/02AAE597-3865-456C-AE7F-613F99F850A8/sqljdbc_4.2.6420.100_enu.tar.gz");
        downloader.prepare();
        downloader.start();
    }
}
