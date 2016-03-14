package com.studio.service.xls;

import com.google.gson.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class XLSUtilsTest {

    @Test
    public void testReadListFromXLS() throws Exception {
        String xlsPath = "D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources\\xls_res_03.xls";
        JsonArray ja;
        Assert.assertNotNull(ja = XLSUtils.readListFromXLS(xlsPath));
        System.out.println("" + ja);
    }

    @Test
    public void testSaveJsonArrayToXLS() throws Exception {
        String json = "[{\"name\":\"name\",\"age\":\"age\",\"height\":\"height\"},{\"name\":\"Lily\",\"age\":\"18\",\"height\":\"165\"},{\"name\":\"Lucy\",\"age\":\"20\",\"height\":\"168\"},{\"name\":\"Lilei\",\"age\":\"22\",\"height\":\"180\"},{},{\"name\":\"Jim\",\"age\":\"21\",\"height\":\"155\"}]";
        JsonElement je = new JsonParser().parse(json);
        if (je.isJsonArray()) {
            String xlsPath = "D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources\\xls_res_03_create.xls";
            boolean result = XLSUtils.saveJsonArrayToXLS((JsonArray) je, xlsPath);
            Assert.assertEquals(true, result);
        }

    }

    @Test
    public void testGetKeyOfJsonObject() throws Exception {
        String json = "{\"name\":\"Lily\",\"age\":\"18\",\"height\":\"165\"}";
        List list = XLSUtils.getKeysOfJsonObject((JsonObject) new JsonParser().parse(json));
        System.out.println("list:" + list);
        Assert.assertNotNull(list);
    }
}