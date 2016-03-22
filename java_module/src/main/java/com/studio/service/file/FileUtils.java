package com.studio.service.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        String dir = path.substring(0, path.lastIndexOf(File.pathSeparator));
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

    public static String readTextFromFile(File f) {
        if (f.exists()) {
            FileReader fr = null;
            char[] buffer = new char[512];
            StringBuffer sb = new StringBuffer();
            try {
                fr = new FileReader(f);
                int len;
                while (true) {
                    len = fr.read(buffer, 0, buffer.length);
                    if (len == -1) {
                        break;
                    }
                    sb.append(buffer, 0, len);
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fr != null) {
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public static void writeTextToFile(File f, String text) {
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedOutputStream bos = null;
        byte[] textBytes = text.getBytes();
        try {
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int leave = textBytes.length;
            int offset = 0;
            int writeLen = 0;
            while (true) {
                if (leave > 1024) {
                    writeLen = 1024;
                } else {
                    writeLen = leave;
                }
                bos.write(textBytes, offset, writeLen);
                leave -= writeLen;
                if (leave <= 0) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
