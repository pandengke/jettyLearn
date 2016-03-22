package com.studio.service.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class FileUtilsTest {

    @Test
    public void testSaveStringToFile() throws Exception {
        String root = "D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources\\";
        String json = "json.txt";
        boolean ret = FileUtils.saveStringToFile("123", root + json);
        Assert.assertEquals(true, ret);
    }

    @Test
    public void testCreateFileAutoIncrese() throws Exception {
        String root = "D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources\\";
        String json = "json1.txt";
        File f = FileUtils.createFileAutoIncrese(root + json);
        Assert.assertNotNull(f);
    }

    @Test
    public void testLoadFiles() throws Exception {
        String root = "D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources\\root";
        List<File> files = FileUtils.loadFiles(root);
        for (File each : files) {
            System.out.println(each.getPath());
        }
        Assert.assertNotNull(files);
    }

    @Test
    public void testLoadFiles1() throws Exception {
        System.out.println("******************");
        String root = "D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources";
        List<File> files = FileUtils.loadFiles(root, ".txt");
        for (File each : files) {
            System.out.println(each.getPath());
        }
        Assert.assertNotNull(files);
    }

    @Test
    public void testWriteTextToFile() throws Exception {
        File f = new File("D://test_download/a.txt");
        FileUtils.writeTextToFile(f, "abcde" +
                "fg");
    }
}