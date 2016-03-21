package com.studio.service.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/21.
 */
public class FileUtils {

    public static boolean saveStringToFile(String str, String path) {

        PrintStream fos = null;
        try {
            File file = createFileAutoIncrese(path);
            if (file != null && file.exists()) {
                fos = new PrintStream(file);
                fos.print(str);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        return false;
    }

    public static File createFileAutoIncrese(String path) throws IOException {
        if (path == null) {
            return null;
        }
        String dir = path.substring(0, path.lastIndexOf('\\'));
        new File(dir).mkdir();
        File file = new File(path);

        if (file.exists()) {
            String fileName = path.substring(0, path.lastIndexOf("."));
            String surfix = path.substring(path.lastIndexOf('.'));
            file = new File(fileName + "_new" + surfix);
        }
        file.createNewFile();
        return file;
    }

    public static List<File> loadFiles(String root) {
        List<File> files = new ArrayList<>();
        File f = new File(root);
        File[] list = f.listFiles();
        for (File each : list) {
            if (each.isDirectory()) {
                files.addAll(loadFiles(each.getPath()));
            } else {
                files.add(each);
            }
        }
        return files;
    }

    public static List<File> loadFiles(String root, String regular) {
        Pattern pattern = Pattern.compile(regular);
        List<File> files = new ArrayList<>();
        File f = new File(root);
        File[] list = f.listFiles();
        for (File each : list) {
            if (each.isDirectory()) {
                files.addAll(loadFiles(each.getPath(), regular));
            } else {
                Matcher m = pattern.matcher(each.getPath());
                if (m.find()) {
                    files.add(each);
                }
            }
        }
        return files;
    }
}
