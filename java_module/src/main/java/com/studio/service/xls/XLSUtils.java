package com.studio.service.xls;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.EmptyCell;
import jxl.read.biff.BiffException;
import jxl.read.biff.SharedStringFormulaRecord;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/14.
 */
public class XLSUtils {
    public static JsonArray readListFromXLS(String xlsPath) {
        Workbook workbook = null;
        if (xlsPath == null) {
            return null;
        }
        if (xlsPath.endsWith(".xlsx")) {
            throw new IllegalArgumentException("this method can not support excel 2007,please conver to excel 2003");
        }
        try {
            workbook = Workbook.getWorkbook(new File(xlsPath));
            for (Sheet sheet : workbook.getSheets()) {
                List<String> keys = new ArrayList<>();
                {
                    Cell[] cells = sheet.getRow(0);
                    for (int i = 0; i < cells.length; i++) {
                        keys.add(cells[i].getContents());
                    }
                }
                JsonArray ja = new JsonArray();
                for (int i = 0; i < sheet.getRows(); i++) {
                    JsonObject jo = new JsonObject();
                    Cell[] cells = sheet.getRow(i);
                    for (int j = 0; j < cells.length; j++) {
                        jo.add(keys.get(j), new JsonPrimitive(cells[j].getContents()));
                    }
                    ja.add(jo);
                }
                return ja;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
            System.out.print("not support xls 2007(.xlsx)");
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return null;
    }

    public static boolean saveJsonArrayToXLS(JsonArray ja, String xlsPath) {
        if (ja == null) {
            throw new NullPointerException("ja is null");
        }
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(new File(xlsPath));
            wwb.createSheet("Sheet1", 0);
            WritableSheet sheet = wwb.getSheet(0);
            List<String> keys = getKeysOfJsonObject((JsonObject) ja.get(0));
            for (int rowIndex = 0; rowIndex < ja.size(); rowIndex++) {
                JsonElement je = ja.get(rowIndex);
                //je -> row;
                if (je.isJsonObject()) {
                    JsonObject jo = (JsonObject) je;
                    if (jo != null) {
                        for (int columnIndex = 0; columnIndex < keys.size(); columnIndex++) {
                            JsonElement jeColumn = jo.get(keys.get(columnIndex));
                            if (jeColumn != null) {
                                String value = jeColumn.getAsString();
                                Label label = new Label(columnIndex,rowIndex,value);
                                sheet.addCell(label);
                            }
                        }
                    }
                }
            }
            wwb.write();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            if (wwb != null) {
                try {
                    wwb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static List<String> getKeysOfJsonObject(JsonObject jo) {
        List<String> keys = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> set = jo.entrySet();
        for (Map.Entry<String, JsonElement> entry : set) {
            keys.add(entry.getKey());
        }
        return keys;
    }
}
