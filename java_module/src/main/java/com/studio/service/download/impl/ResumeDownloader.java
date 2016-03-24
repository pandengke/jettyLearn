package com.studio.service.download.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.studio.service.download.BaseDownloader;
import com.studio.service.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
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
        int offset;
        int end;

        public Task(int piece, int start, int offset, int end) {
            this.piece = piece;
            this.start = start;
            this.offset = offset;
            this.end = end;
        }

        public boolean isComplete() {
            return start >= end;
        }
    }

    private String jsonFileName;
    private List<Task> tasks;

    private int taskCount = 5;

    private File jsonFile;

    private ExecutorService service;

    private int totalLen;
    private int totalDownload;
    private String jsonConfig;

    @Override
    public void prepare() {

        tasks = loadTasks();
        try {
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            totalLen = conn.getContentLength();

            if (tasks == null && totalLen != -1) {
                tasks = createTask(totalLen, getTaskCount());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tasks != null) {
            updateRecord();
            setDownloadStatus(DownloadStatus.READY);
        }

    }

    private synchronized void updateRecord() {
        jsonConfig = new Gson().toJson(tasks);
        saveTasks();
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
            Task task = new Task(i, start, 0, end);
            tasks.add(task);
        }
        return tasks;
    }

    private List<Task> loadTasks() {
        String folder = getSaveDirectoryPath();
        jsonFileName = getUrl().substring(getUrl().lastIndexOf("/") + 1) + ".json";
        jsonFile = new File(folder + File.separatorChar + jsonFileName);
        String jsonConfig = FileUtils.readTextFromFile(jsonFile);
        return new Gson().fromJson(jsonConfig, new TypeToken<List<Task>>() {
        }.getType());
    }

    private void saveTasks() {
        String folder = getSaveDirectoryPath();
        jsonFileName = getUrl().substring(getUrl().lastIndexOf("/") + 1) + ".json";
        jsonFile = new File(folder + File.separatorChar + jsonFileName);
        FileUtils.writeTextToFile(jsonFile, jsonConfig);
    }

    @Override
    public void start() {
        if (getDownloadStatus() != DownloadStatus.READY) {
            throw new IllegalStateException("the download status is not ready");
        }
        for (Task task : tasks) {
            totalDownload += task.offset;
        }
        System.out.println("start percent:" + totalDownload / (float) totalLen);
        setDownloadStatus(DownloadStatus.RUNNING);
        service = Executors.newFixedThreadPool(getThreadCount());
        for (Task task : tasks) {
            if (!task.isComplete()) {
                service.execute(() -> write(task));
            }
        }
    }

    private void write(Task task) {
        System.out.println("start task:" + task.piece);
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024 * 512];
        try {
            String folder = getSaveDirectoryPath();
            String fileName = getUrl().substring(getUrl().lastIndexOf('/') + 1);
            RandomAccessFile file = new RandomAccessFile(folder + File.separatorChar + fileName, "rw");
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Range", "bytes=" + task.start + "-" + task.end);
            bis = new BufferedInputStream(conn.getInputStream());
            int readLen;
            while (getDownloadStatus() == DownloadStatus.RUNNING) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readLen = bis.read(buffer, 0, buffer.length);
                if (readLen == -1) {
                    tasks.remove(task);
                    updateRecord();
                    break;
                }
                file.seek(task.start + task.offset);
                file.write(buffer, 0, readLen);
                task.offset += readLen;
                totalDownload += readLen;
                synchronized (this) {
                    onProgressChange((float) totalDownload / totalLen);
                    updateRecord();
                }
                if (task.start + task.offset >= task.end) {
                    task.start = task.end;
                    break;
                }
            }
            file.close();
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
            onProgressChange((float) totalDownload / totalLen);
            updateRecord();
            boolean complete = true;
            for (Task each : tasks) {
                complete = complete && each.isComplete();
            }
            if (complete) {
                stop();
            }
        }
    }

    @Override
    public void pause() {
        setDownloadStatus(DownloadStatus.STOPPED);
    }

    @Override
    public void stop() {
        service.shutdown();
        if (jsonFile != null) {
            jsonFile.delete();
        }
        setDownloadStatus(DownloadStatus.READY);
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
