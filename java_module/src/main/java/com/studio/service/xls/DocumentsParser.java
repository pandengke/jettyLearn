package com.studio.service.xls;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.studio.service.xls.pojo.DocInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DocumentsParser {

    public List<DocInfo> parseFromXls(String filePath) {
        List<DocInfo> list = null;
        JsonArray jsonArray = XLSUtils.readListFromXLS(filePath);
        list = new Gson().fromJson(jsonArray, new TypeToken<List<DocInfo>>() {
        }.getType());
        return list;
    }

    public void packToXls(String filePath, List<DocInfo> list) {
        JsonArray ja = new JsonArray();
        for (DocInfo doc : list) {
            ja.add(new JsonPrimitive(doc.toString()));
        }
        XLSUtils.saveJsonArrayToXLS(ja, filePath);
    }

}
