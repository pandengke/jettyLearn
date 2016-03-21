package com.studio.service.download.impl;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/3/21.
 */
public class ResumeDownloaderTest {

    @Test
    public void testCreateTask() throws Exception {
        ResumeDownloader downloader = new ResumeDownloader();
        List<ResumeDownloader.Task> tasks = downloader.createTask(13, 4);
        Assert.assertNotNull(tasks);
    }
}