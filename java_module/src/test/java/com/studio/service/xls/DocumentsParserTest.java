package com.studio.service.xls;

import com.studio.service.xls.pojo.DocInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DocumentsParserTest {

    @Test
    public void testParseFromXls() throws Exception {
        DocumentsParser parser = new DocumentsParser();
        List<DocInfo> list = parser.parseFromXls("D:\\tmp\\sample.xls");
        System.out.println("parse:" + list);
        Assert.assertNotNull(list);
        for (DocInfo info : list) {
            try {
                File[] files = info.getRelativeFiles("D:\\tmp\\image");
                System.out.print("files:" + toString(files));
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void testPackToXls() throws Exception {

    }

    public static String toString(File[] files) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < files.length; i++) {
            sb.append("\r\nfile:\r\n").
                    append(files[i].getAbsolutePath()).append("\r\n");
        }
        return sb.toString();
    }
}