package com.studio.service.download.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.studio.service.download.BaseDownloader;
import com.studio.service.file.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/3/21.
 */
public class ResumeDownloader extends BaseDownloader {

    public class Task {
        int piece;
        int start;
        long end;

        public Task(int piece, int start, long end) {
            this.piece = piece;
            this.start = start;
            this.end = end;
        }
    }

    private String jsonFileName;
    private List<Task> tasks;

    private int taskCount = 5;

    private File jsonFile;

    private ExecutorService service;

    @Override
    public void prepare() {

        tasks = loadTasks();
        try {
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (tasks == null) {
                tasks = createTask(conn.getContentLength(), getTaskCount());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tasks != null) {
            setDownloadStatus(DownloadStatus.READY);
        }

    }

    public List<Task> createTask(int totalLength, int taskCount) {
        int partLength = (int) ((float) totalLength / taskCount);
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskCount + 1; i++) {
            int start = partLength * i;
            int end = start + partLength - 1;
            if (end > totalLength) {
                end = totalLength;
            }
            Task task = new Task(i, start, end);
            tasks.add(task);
        }
        return tasks;
    }

    private List<Task> loadTasks() {
        String folder = getSaveDirectoryPath();
        jsonFileName = getUrl().substring(getUrl().lastIndexOf('/') + 1) + ".json";
        jsonFile = new File(folder + jsonFileName);
        String jsonConfig = FileUtils.readTextFromFile(jsonFile);
        return new Gson().fromJson(jsonConfig, new TypeToken<List<Task>>() {
        }.getType());
    }

    @Override
    public void start() {
        if (getDownloadStatus() != DownloadStatus.READY) {
            throw new IllegalStateException("the download status is not ready");
        }
        service = Executors.newFixedThreadPool(getThreadCount());
        runningCount = tasks.size();
        for (Task task : tasks) {
            service.execute(() -> write(task));
        }
    }

    private void write(Task task) {

        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024 * 512];
        try {
            String folder = getSaveDirectoryPath();
            String fileName = getUrl().substring(getUrl().lastIndexOf('/') + 1);
            RandomAccessFile file = new RandomAccessFile(folder + "\\" + fileName, "rw");
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Range", "bytes="+task.start + "-" + task.end);
            conn.getInputStream();
            bis = new BufferedInputStream(conn.getInputStream());
            int readLen;
            int offset = 0;
            while (getDownloadStatus() == DownloadStatus.RUNNING) {
                readLen = bis.read(buffer, 0, buffer.length);
                if (readLen == -1) {
                    break;
                }
//                onProgressChange((float) sum / totalLen);
                file.seek(task.start + offset);
                offset += readLen;
                file.write(buffer, 0, readLen);
            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        runningCount--;
        if (runningCount == 0) {
            service.shutdown();
        }
    }

    int runningCount = 0;

    @Override
    public void pause() {
        setDownloadStatus(DownloadStatus.STOPPED);
    }

    @Override
    public void stop() {

        //删除配置文件；
        if (jsonFile != null) {
            jsonFile.delete();
        }
    }

    @Override
    public void onProgressChange(float percent) {
        throw new UnsupportedOperationException("you must override this method");
    }


    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        if (taskCount < 1) {
            throw new IllegalArgumentException("taskCount must bigger than 1");
        }
        this.taskCount = taskCount;
    }
}
